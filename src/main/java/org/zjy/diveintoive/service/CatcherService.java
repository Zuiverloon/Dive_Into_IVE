package org.zjy.diveintoive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjy.diveintoive.utils.ConstantUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CatcherService {

    @Autowired
    private ConstantUtil constantUtil;

    public List<String> iveImages(){
        File dir = new File(constantUtil.LOCAL_STORAGE_PATH);
        File[] files = dir.listFiles();
        String serverPathPrefix = constantUtil.getIVEServerResourcePath();
        if (files!=null && files.length>0){
            List<String> res = new ArrayList<>();
            for (File f:files) {
                String filename = f.getName();
                if (hasValidSuffix(filename)) res.add(serverPathPrefix+filename);
            }
            Collections.sort(res);
            return res;
        }
        return new ArrayList<>();
    }



    public String getPath(){
        return constantUtil.LOCAL_STORAGE_PATH;
    }

    public boolean hasValidSuffix(String filename){
        return filename.contains("jpeg") || filename.contains("gif");
    }
}
