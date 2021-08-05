package com.chen.sample1.web.service;

/**
 * 缓存接口
 */
public interface ICacheService {
    /**
     * 获取缓存信息
     * @param key
     * @return
     */
    Object get(Object key);


    /**
     * 获取缓存信息，返回指定类型对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(Object key, Class<T> clazz);

    long atomicGet(String key);
    /**
     *  设置缓存信息，默认超时时间
     * @param key
     * @param value
     */
    void put(Object key, Object value);

    /**
     *  设置缓存信息，无过期时间
     * @param key
     * @param value
     */
    void putNonExp(Object key, Object value);

    /**
     *  设置缓存信息，超时
     * @param key
     * @param value
     * @param timeToLiveSeconds 存活时间，单位：毫秒
     */
    void put(Object key, Object value, long timeToLiveSeconds);

    /**
     * 如果为空就set值，并返回true；如果存在(不为空)不进行操作，并返回false
     * @param key
     * @param value
     * @return
     */
    boolean putIfAbsent(Object key, Object value);

    /**
     * 往列表左边中添加元素
     */
    void lpush(String key, Object value);

    /**
     * 往列表右边中弹出元素
     */
    <T> T rpop(String key, Class<T> clazz);


    /**
     * 获取列表中元素长度
     * @param key
     * @return
     */
    Long llen(String key);

    /**
     * 移除缓存信息
     * @param key
     * @return
     */
    boolean remove(Object key);

    /**
     * 获取默认失效时间，单位（毫秒）
     * @return 默认失效时间
     */
    Long getDefaultExpireTime();

    /**
     * 获取失效时间，单位（毫秒）
     * @param key
     * @return
     */
    Long getExpire(String key);

}

