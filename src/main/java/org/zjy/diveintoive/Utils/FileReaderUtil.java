package org.zjy.diveintoive.Utils;

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
}
