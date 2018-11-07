package com.example.shopping.services.redis;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class RedisServiceImpl implements RedisService {
    private final Jedis jedis;

    public RedisServiceImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void delWhiteList(String token) {
        this.jedis.del(token);
    }

    @Override
    public void setBlackList(String token) {
        this.jedis.set(token, "setBlackList");
    }

    @Override
    public Boolean isBlackList(String token) {
        String isFound = jedis.get(token);
        if (isFound != null) {
            return true;
        }
        return false;
    }
}
