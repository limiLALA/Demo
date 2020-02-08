package com.ninn.demo.redis;

import redis.clients.jedis.Jedis;

interface RedisTask {
    void run(Jedis jedis);
}
