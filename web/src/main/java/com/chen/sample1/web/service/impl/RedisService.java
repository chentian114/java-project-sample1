package com.chen.sample1.web.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.chen.sample1.web.service.ICacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存实现
 */
@Component
public class RedisService implements ICacheService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /** redis默认失效时间1天（单位：毫秒） */
    private final long defaultExpiration = 1 * 24 * 3600 * 1000L;

    @Override
    public Object get(Object key) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public <T> T get(Object key, Class<T> clazz) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return JSONUtil.toBean(JSONUtil.toJsonStr(valueOperations.get(key)), clazz);
    }
    @Override
    public long atomicGet(String key){
        RedisAtomicLong counter = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        return counter.get();
    }
    @Override
    public void put(Object key, Object value) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key.toString(), value, defaultExpiration, TimeUnit.MILLISECONDS);
    }

    @Override
    public void putNonExp(Object key, Object value) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key.toString(), value);
    }

    @Override
    public void put(Object key, Object value, long timeToLiveSeconds) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key.toString(), value, timeToLiveSeconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean putIfAbsent(Object key, Object value){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.setIfAbsent(key.toString(), value);
    }

    @Override
    public void lpush(String key, Object value){
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key,value);
    }

    @Override
    public <T> T rpop(String key,Class<T> clazz){
        try {
            ListOperations<String, Object> listOperations = redisTemplate.opsForList();
            Object obj = listOperations.rightPop(key);
            if (obj == null) {
                return null;
            }
            return JSONUtil.toBean(String.valueOf(obj), clazz);
        }catch (Exception e){
            logger.error("key:{} error:",key,e);
            return null;
        }
    }

    @Override
    public Long llen(String key){
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        return listOperations.size(key);
    }

    @Override
    public boolean remove(Object key) {
        if (key == null) {
            return false;
        }
        String keyStr = String.valueOf(key);
        if (StrUtil.isBlank(keyStr)) {
            return false;
        }
        return redisTemplate.delete(keyStr);
    }

    @Override
    public Long getDefaultExpireTime() {
        return defaultExpiration;
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key,TimeUnit.MILLISECONDS);
    }




}
