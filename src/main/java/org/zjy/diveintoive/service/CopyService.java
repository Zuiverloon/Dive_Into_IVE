package org.zjy.diveintoive.service;

import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjy.diveintoive.utils.ConstantUtil;
import org.zjy.diveintoive.utils.FileCopyUtil;
import org.zjy.diveintoive.utils.HashUtil;
import org.zjy.diveintoive.utils.RedisUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class CopyService {


    @Data
    @Builder
    private static class ProcessStatus {
        private String filePath;
        private boolean success;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CopyService.class);

    @Autowired
    private ConstantUtil constantUtil;
    @Autowired
    private FileCopyUtil fileCopyUtil;

    @Autowired
    private HashUtil hashUtil;

    @Autowired
    private RedisUtil redisUtil;

    ExecutorService executorService;

    public CopyService() {
        executorService = Executors.newFixedThreadPool(5);
    }

    public List<String> localCopy(String memberName) {
        List<String> failedFileNames = new ArrayList<>();
        File srcDir = new File(constantUtil.LOCAL_STORAGE_PATH + memberName + "_Local");
        File[] srcFiles = srcDir.listFiles();
        if (srcFiles == null) {
            LOGGER.warn("Empty folder!");
            return failedFileNames;
        }

        // copy images asynchronously
        List<CompletableFuture<ProcessStatus>> collect = Arrays.stream(srcFiles).map(f -> {
            CompletableFuture<ProcessStatus> future =
                    CompletableFuture.supplyAsync(() -> executeCopy(f, memberName), executorService);
            return future;
        }).collect(Collectors.toList());

        // wait for all threads to join
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[]{})).join();

        // check any thread fail
        for (int i = 0; i < srcFiles.length; i++) {
            CompletableFuture<ProcessStatus> res = collect.get(i);
            try {
                if (!res.get().isSuccess()) {
                    failedFileNames.add(res.get().getFilePath());
                }
            } catch (InterruptedException | ExecutionException e) {
                failedFileNames.add(srcFiles[i].getPath());
            }
        }
        return failedFileNames;
    }

    public ProcessStatus executeCopy(File f, String memberName) {
        LOGGER.info("{} is being copied", f.getPath());
        ProcessStatus processStatus = ProcessStatus.builder().filePath(f.getPath()).success(true).build();
        if (isHiddenFile(f)) {
            LOGGER.info("{} is a hidden file", f.getPath());
            return processStatus;
        }
        if (isDup(f)) {
            LOGGER.warn("{} is duplicated", f.getPath());
            f.renameTo(new File(f.getParent() + "/dup_" + f.getName()));
            return processStatus;
        }
        String srcFilePath = null;
        String dstFileName = null;
        if (isGIF(f)) {
            LOGGER.warn("{} is a gif", f.getPath());
            srcFilePath = f.getPath();
            dstFileName = memberName + "_" + System.currentTimeMillis() + ".gif";
        } else {
            LOGGER.warn("{} is an image", f.getPath());
            dstFileName = memberName + "_" + System.currentTimeMillis() + ".jpeg";
            String jpgPath = f.getParent() + "/" + dstFileName;
            if (generateJPEG(f.getPath(), jpgPath)) {
                srcFilePath = jpgPath;
                f.delete();
            } else {
                LOGGER.info(f.getPath() + " copy failed");
                processStatus.setSuccess(false);
                return processStatus;
            }
        }
        if (srcFilePath != null && dstFileName != null) {
            boolean status = fileCopyUtil.copy(srcFilePath, constantUtil.LOCAL_STORAGE_PATH + dstFileName)
                    && fileCopyUtil.copy(srcFilePath, constantUtil.REMOTE_STORAGE_PATH + dstFileName);
            if (status) {
                File file = new File(srcFilePath);
                file.delete();
            }
            processStatus.setSuccess(status);
            return processStatus;
        }
        processStatus.setSuccess(false);
        return processStatus;
    }

    private boolean generateJPEG(String src, String dest) {
        LOGGER.info("Generating JPG from {} to {}", src, dest);
        try {
            Process process = Runtime.getRuntime().exec("sips -s format jpeg " + src + " --out " + dest);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private boolean isHiddenFile(File f) {
        return f.getName().startsWith(".");
    }

    private boolean isGIF(File f) {
        String filename = f.getName();
        return filename.endsWith("gif") || filename.endsWith("GIF");
    }

    private boolean isDup(File f) {
        String hashString = hashUtil.getHashStringByFile(f);
        if (hashString == null) {
            System.out.println("Hash Fail!");
            return false;
        }
        String key = constantUtil.HASH_PREFIX + hashString;
        if (redisUtil.keyExists(key)) {
            return true;
        } else {
            redisUtil.setKey(key);
            return false;
        }
    }
}
