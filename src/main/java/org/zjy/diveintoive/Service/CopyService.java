package org.zjy.diveintoive.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zjy.diveintoive.Utils.ConstantUtil;
import org.zjy.diveintoive.Utils.FileCopyUtil;
import org.zjy.diveintoive.Utils.HashUtil;
import org.zjy.diveintoive.Utils.RedisUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class CopyService {
    @Autowired
    private ConstantUtil constantUtil;
    @Autowired
    private FileCopyUtil fileCopyUtil;

    @Autowired
    private HashUtil hashUtil;

    @Autowired
    private RedisUtil redisUtil;

    ExecutorService executorService;

    public CopyService(){
        executorService = Executors.newFixedThreadPool(5);
    }

    public int localCopy(String name){
        File srcDir = new File(constantUtil.STORAGE_PATH+name+"_Local");
        File[] srcFiles = srcDir.listFiles();
        if (srcFiles == null){
            System.out.println("Empty folder!");
            return 0;
        }
        boolean allSuccess = true;

        // copy images asynchronously
        List<CompletableFuture<Boolean>> collect = Arrays.stream(srcFiles).map(f->{
            CompletableFuture<Boolean> future =
                    CompletableFuture.supplyAsync(()->executeCopy(f,name), executorService);
            return future;
        }).collect(Collectors.toList());

        // wait for all threads to join
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[]{})).join();

        // check any thread fail
        for (CompletableFuture<Boolean> res:collect){
            try{
                if (!res.get())allSuccess = false;
            }catch (InterruptedException | ExecutionException e){
                allSuccess = false;
            }
        }
        return allSuccess?1:-1;
    }

    public Boolean executeCopy(File f,String name){
        String srcFilePath = f.getPath();
        System.out.println(srcFilePath+" copying");
        if (isHiddenFile(f)){
            System.out.println(srcFilePath+" is a hidden file!");
            return true;
        }
        if (isDup(f)){
            System.out.println(srcFilePath+" is duplicated");
            f.renameTo(new File(f.getParent()+"/dup_"+f.getName()));
            return true;
        }
        if (isGIF(f)){
            String destFilePath = constantUtil.STORAGE_PATH+name+"_"+System.currentTimeMillis()+".gif";
            if (fileCopyUtil.copy(f.getPath(),destFilePath)){
                boolean deleteRes = f.delete();
                if (!deleteRes){
                    System.out.println(f.getPath()+" delete unsuccessfully!");
                }
            }
            else{
                System.out.println(srcFilePath+" copy failed");
                return false;
            }
        }
        else{
            String destFilePath = constantUtil.STORAGE_PATH+name+"_"+System.currentTimeMillis()+".jpeg";
            if (generateJPEG(f.getPath(),destFilePath)){
                boolean deleteRes = f.delete();
                if (!deleteRes){
                    System.out.println(f.getPath()+" delete unsuccessfully");
                }
            }
            else{
                System.out.println(srcFilePath+" copy failed");
                return false;
            }
        }
        return true;
    }

    private boolean generateJPEG(String src,String dest){
        try{
            Process process = Runtime.getRuntime().exec("sips -s format jpeg "+src+" --out "+dest);
            process.waitFor();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private boolean isHiddenFile(File f){
        return f.getName().startsWith(".");
    }

    private boolean isGIF(File f){
        String filename = f.getName();
        return filename.endsWith("gif") || filename.endsWith("GIF");
    }

    private boolean isDup(File f){
        String hashString = hashUtil.getHashStringByFile(f);
        if (hashString == null){
            System.out.println("Hash Fail!");
            return false;
        }
        String key = constantUtil.HASH_PREFIX+hashString;
        if (redisUtil.keyExists(key)){
            return true;
        }
        else{
            redisUtil.setKey(key);
            return false;
        }
    }
}
