package org.zjy.diveintoive.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjy.diveintoive.Utils.ConstantUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatcherService {

    @Autowired
    private ConstantUtil constantUtil;

    public List<String> iveImages(){
        File dir = new File(constantUtil.STORAGE_PATH);
        File[] files = dir.listFiles();
        String serverPathPrefix = constantUtil.getIVEServerResourcePath();
        if (files!=null && files.length>0){
            List<String> res = new ArrayList<>();
            for (File f:files) {
                String filename = f.getName();
                if (filename.contains("jpeg")) res.add(serverPathPrefix+filename);
            }
            return res;
        }
        return new ArrayList<>();
    }



    public String getPath(){
        return constantUtil.STORAGE_PATH;
    }
}
