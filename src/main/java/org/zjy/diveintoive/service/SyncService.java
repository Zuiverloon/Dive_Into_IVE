package org.zjy.diveintoive.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjy.diveintoive.utils.ConstantUtil;
import org.zjy.diveintoive.utils.FileCopyUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncService.class);

    @Autowired
    ConstantUtil constantUtil;

    @Autowired
    FileCopyUtil fileCopyUtil;

    public List<String> sync() {
        List<String> failedFileNames = new ArrayList<>();
        String LOCAL_DIR = constantUtil.LOCAL_STORAGE_PATH;
        String REMOTE_DIR = constantUtil.REMOTE_STORAGE_PATH;
        Set<String> localDirFileNames = getDirFileList(LOCAL_DIR);
        Set<String> remoteDirFileNames = getDirFileList(REMOTE_DIR);
        if (localDirFileNames == null) {
            return failedFileNames;
        }
        if (remoteDirFileNames == null) {
            remoteDirFileNames = new HashSet<>();
        }
        for (String fileName : localDirFileNames) {
            if (!remoteDirFileNames.contains(fileName)) {
                LOGGER.info("{} is copying to remote", fileName);
                if (!fileCopyUtil.copy(LOCAL_DIR + fileName, REMOTE_DIR + fileName)) {
                    failedFileNames.add(LOCAL_DIR + fileName);
                }
            }
        }
        return failedFileNames;
    }

    public boolean check() {
        String LOCAL_DIR = constantUtil.LOCAL_STORAGE_PATH;
        String REMOTE_DIR = constantUtil.REMOTE_STORAGE_PATH;
        Set<String> localDirFileNames = getDirFileList(LOCAL_DIR);
        Set<String> remoteDirFileNames = getDirFileList(REMOTE_DIR);
        if (localDirFileNames == null) {
            return true;
        }
        if (remoteDirFileNames == null) {
            return false;
        }
        return remoteDirFileNames.containsAll(localDirFileNames);
    }

    private Set<String> getDirFileList(String dir) {
        File srcDir = new File(dir);
        File[] srcFiles = srcDir.listFiles();
        if (srcFiles == null) return null;
        return Arrays.stream(srcFiles).map(File::getName).collect(Collectors.toSet());
    }
}
