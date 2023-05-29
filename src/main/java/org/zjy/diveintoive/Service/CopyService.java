package org.zjy.diveintoive.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjy.diveintoive.Utils.ConstantUtil;

import java.io.File;
import java.io.IOException;

@Service
public class CopyService {
    @Autowired
    private ConstantUtil constantUtil;
    public int localCopy(String name){
        File srcDir = new File(constantUtil.STORAGE_PATH+name+"_Local");
        File[] srcFiles = srcDir.listFiles();
        if (srcFiles == null){
            System.out.println("Empty folder!");
            return 0;
        }
        boolean allSuccess = true;
        for (File f:srcFiles){
            System.out.println(f.getPath()+" copying");
            if (isHiddenFile(f))continue;
            String destFileName = constantUtil.STORAGE_PATH+name+"_"+System.currentTimeMillis()+".jpeg";
            if (generateJPEG(f.getPath(),destFileName)){
                boolean deleteRes = f.delete();
                if (!deleteRes){
                    System.out.println(f.getPath()+" delete unsuccessfully");
                }
            }
            else{
                allSuccess = false;
            }

        }
        return allSuccess?1:0;
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
}
