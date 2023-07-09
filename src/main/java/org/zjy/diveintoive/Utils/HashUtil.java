package org.zjy.diveintoive.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashUtil {
    @Autowired
    FileReaderUtil fileReaderUtil;
    public String getHashStringByFile(File f){
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algo");
            return null;
        }
        byte[] fileByte = fileReaderUtil.readFileAsBytes(f.getPath());
        if (fileByte.length>0){
            return byte2Hex(messageDigest.digest(fileByte));
        }
        else{
            return null;
        }
    }

    private String byte2Hex(byte[] bytes){
        //return new String(bytes);
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte b:bytes){
            temp = Integer.toHexString(b & 0xFF);
            if (temp.length() == 1){
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }
}
