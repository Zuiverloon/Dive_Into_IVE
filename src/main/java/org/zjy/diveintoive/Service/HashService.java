package org.zjy.diveintoive.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjy.diveintoive.Utils.ConstantUtil;
import org.zjy.diveintoive.Utils.FileReaderUtil;
import org.zjy.diveintoive.Utils.HashUtil;
import org.zjy.diveintoive.Utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class HashService {
    @Autowired
    ConstantUtil constantUtil;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    FileReaderUtil fileReaderUtil;

    @Autowired
    HashUtil hashUtil;
    public int genHash(){
        File dir = new File(constantUtil.STORAGE_PATH);
        File[] files = dir.listFiles();
        if (files == null){
            System.out.println("Empty folder!");
            return 0;
        }
        for (File f:files){
            System.out.println(f.getPath());
            if (!(f.getPath().endsWith("jpeg") || f.getPath().endsWith("gif")))continue;
            String hashString = hashUtil.getHashStringByFile(f);
            if (hashString == null){
                System.out.println("hash fail!");
            }
            //write into redis
            //System.out.println(hashString);
            String key = constantUtil.HASH_PREFIX+hashString;
            redisUtil.setKey(key);
        }
        return 1;
    }

    public List<String> getHash(){
        Jedis jedis = redisUtil.getJedis();
        String pattern = constantUtil.HASH_PREFIX+"*";
        Set<String> hashStrings = jedis.keys(pattern);
        System.out.println(hashStrings.size());
        return new ArrayList<>(hashStrings);
    }

    public int deleteHash(){
        List<String> hashString = getHash();
        Jedis jedis = redisUtil.getJedis();
        for (String s:hashString){
            jedis.del(s);
        }
        return 1;
    }


}
