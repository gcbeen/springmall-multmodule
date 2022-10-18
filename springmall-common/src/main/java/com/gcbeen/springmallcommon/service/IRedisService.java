package com.gcbeen.springmallcommon.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis 操作 Service
 * 对象和数组以 json 的形式进行存储
 */
public interface IRedisService {

    /**
     * 存储数据
     * @param key
     * @param value
     * @param time
     */
    void set(String key, Object value, long time);

    /**
     * 存储数据
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 获取数据
     * @param key
     * @return Object
     */
    Object get(String key);

    /**
     * 删除数据
     * @param key
     * @return
     */
    Boolean remove(String key);

    /**
     * 删除数据
     * @param keys
     * @return
     */
    Long remove(List<String> keys);

    /**
     * 设置超期时间
     * @param key
     * @param expire
     * @return Boolean
     */
    Boolean expire(String key, long expire);

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 判断是否有该属性
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     * 自增操作
     * @param key
     * @param step 自增步长
     * @return Long
     */
    Long increment(String key, long step);

    /**
     * 自减操作
     * @param key
     * @param step
     * @return
     */
    Long decrement(String key, long step);

    // Hash 结构 ############################################
    
    /**
     * 直接设置 整个 Hash 结构
     * @param key
     * @param map
     * @param time
     * @return
     */
    Boolean hSetAll(String key, Map<String, ?> map, long time);

    /**
     * 直接设置 整个 Hash 结构
     * @param key
     * @param map
     */
    void hSetAll(String key, Map<String, ?> map);

    /**
     * 直接获取整个 Hash 结构
     * @param key
     * @return
     */
    Map<Object, Object> hGetAll(String key);
    
    /**
     * 向 Hash 结构中 设置属性 hashKey 的值
     * @param key
     * @param hashKey
     * @param value
     * @param time
     * @return
     */
    Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * 向 Hash 结构中 设置属性 hashKey 的值
     * @param key
     * @param hashKey
     * @param value
     * 
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * 获取 Hash 结构中的 hashKey 的 属性值
     * @param key
     * @param hashKey Hash中的key
     * @return
     */
    Object hGet(String key, String hashKey);

    /**
     * 删除 Hash 结构中的属性
     * @param key
     * @param hashKey
     */
    void hRemove(String key, Object... hashKey);

    /**
     * 判断 Hash 结构中 是否有该属性
     * @param key
     * @param hashKey
     * @return
     */
    Boolean hHasKey(String key, String hashKey);


    /**
     * Hash 结构中的属性值 自增
     * @param key
     * @param hashkey
     * @param step
     * @return
     */
    Long hIncrement(String key, String hashKey, long step);

    /**
     * Hash 结构中的 属性值 自减
     * @param key
     * @param hashKey
     * @param step
     * @return
     */
    Long hDecrement(String key, String hashKey,long step);

    // Set 结构 ############################################

    /**
     * 向 Set 结构中 添加属性
     * @param key
     * @param values
     * @return
     */
    Long sAdd(String key, Object... values);

    /**
     * 向 Set 结构中 添加属性
     * @param key
     * @param time
     * @param values
     * @return
     */
    Long sAdd(String key, long time, Object... values);

    /**
     * 获取 Set 结构
     * @param key
     * @return
     */
    Set<Object> sMembers(String key);

    /**
     * 是否为 Set 中的属性
     * @param key
     * @param value
     * @return
     */
    Boolean sIsMember(String key, Object value);

    /**
     * 获取 Set 结构 的长度
     * @param key
     * @return
     */
    Long sSize(String key);


    /**
     * 删除 Set 结构中的 属性
     * @param key
     * @param values
     * @return
     */
    Long sRemove(String key, Object... values);

    // List 结构 #############################################
    /**
     * 获取 List 结构中的 属性
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<Object> lRange(String key, long start, long end);


    /**
     * 获取 List 结构的 长度
     * @param key
     * @return
     */
    Long lSize(String key);

    /**
     * 获取 List 结构中的 index 对应的 属性
     * @param key
     * @param index
     * @return
     */
    Object lIndex(String key, long index);

    /**
     * 向 List 结构中 添加属性
     * @param key
     * @param value
     * @return
     */
    Long lPush(String key, Object value);

    /**
     * 向 List 结构中 添加属性
     * @param key
     * @param value
     * @param time
     * @return
     */
    Long lPush(String key, Object value, long time);
    /**
     * 向 List 结构中批量添加属性
     * @param key
     * @param values
     * @return
     */
    Long lPushAll(String key, Object... values);

    /**
     * 向 List 结构中批量添加属性
     * @param key
     * @param time
     * @param values
     * @return
     */
    Long lPushAll(String key, long time, Object... values);

    /**
     * 从 List 结构中移除属性
     * @param key
     * @param count
     * @param value
     * @return
     */
    Long lRemove(String key, long count, Object value);
}