package org.zjy.diveintoive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjy.diveintoive.utils.ConstantUtil;
import org.zjy.diveintoive.utils.FileReaderUtil;
import org.zjy.diveintoive.utils.HashUtil;
import org.zjy.diveintoive.utils.RedisUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class HashService {
    @Autowired
    ConstantUtil constantUtil;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    FileReaderUtil fileReaderUtil;

    ExecutorService executorService;

    @Autowired
    HashUtil hashUtil;

    public HashService() {
        executorService = Executors.newFixedThreadPool(50);
    }


    public int genHash() {
        File dir = new File(constantUtil.LOCAL_STORAGE_PATH);
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("Empty folder!");
            return 0;
        }
        for (File f : files) {
            System.out.println(f.getPath());
            if (!(f.getPath().endsWith("jpeg") || f.getPath().endsWith("gif"))) continue;
            String hashString = hashUtil.getHashStringByFile(f);
            if (hashString == null) {
                System.out.println("hash fail!");
            }
            //write into redis
            //System.out.println(hashString);
            String key = constantUtil.HASH_PREFIX + hashString;
            redisUtil.setKey(key);
        }
        return 1;
    }

    public List<String> getHash() {
        String pattern = constantUtil.HASH_PREFIX + "*";
        return new ArrayList<>(redisUtil.getPattern(pattern));
    }

    public int deleteHash() {
        List<String> hashString = getHash();
        for (String s : hashString) {
            redisUtil.deleteKey(s);
        }
        return 1;
    }


}
