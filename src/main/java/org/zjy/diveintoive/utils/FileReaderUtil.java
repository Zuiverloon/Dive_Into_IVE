package org.zjy.diveintoive.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileReaderUtil {
    public byte[] readFileAsBytes(String path){
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            return new byte[0];
        }
    }

    public String readFileString(String path) {
        return new String(readFileAsBytes(path));
    }
}
