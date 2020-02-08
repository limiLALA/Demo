package com.ninn.demo.service;

import com.ninn.demo.redis.RedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private RedisPool redisPool;




}
