package org.zjy.diveintoive.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Set;

@Component
public class RedisUtil {
    private Jedis jedis;

    @Autowired
    NetworkUtil networkUtil;

    @Getter
    private boolean isActive = false;

    public RedisUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        String ip = networkUtil.getIP();
        jedis = new Jedis(ip, 6379);
    }

    public synchronized int setKey(String key) {
        jedis.set(key, "1");
        return 1;
    }

    public synchronized boolean keyExists(String key) {
        return jedis.exists(key);
    }

    public Set<String> getPattern(String pattern) {
        Set<String> hashStrings = jedis.keys(pattern);
        System.out.println(hashStrings.size());
        return hashStrings;
    }

    public synchronized void deleteKey(String key) {
        jedis.del(key);
    }
}
