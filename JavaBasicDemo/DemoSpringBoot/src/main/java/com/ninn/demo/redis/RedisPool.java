package com.ninn.demo.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisPool {

    JedisPool jedisPool = new JedisPool("localhost", 6379);

    public void execute(RedisTask task){
        try (Jedis jedis = jedisPool.getResource();){
            task.run(jedis);
        }
        return;
    }



}
