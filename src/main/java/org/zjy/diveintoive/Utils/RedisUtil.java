package org.zjy.diveintoive.Utils;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisUtil {
    private static Jedis jedis;


    public Jedis getJedis(){
        if (jedis == null){
            jedis = new Jedis("192.168.0.173",6379);
            System.out.println("Jedis服务正在运行: "+jedis.ping());
        }
        return jedis;
    }
}
