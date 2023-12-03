package org.zjy.diveintoive.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class FileCopyUtil {
    public boolean copy(String src, String dst) {
        File srcFile = new File(src);
        File dstFile = new File(dst);
        try {
            Files.copy(srcFile.toPath(), dstFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
            //throw new RuntimeException(e);
        }
        return true;
    }
}
