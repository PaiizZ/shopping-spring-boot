package com.example.shopping.services.redis;

public interface RedisService {
    void delWhiteList(String jwt);
    void setBlackList(String jwt);
    Boolean isBlackList(String jwt);
}
