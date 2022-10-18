package com.gcbeen.springmalladmin.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import com.gcbeen.springmalladmin.service.IUmsResourceCacheService;
import com.gcbeen.springmallcommon.service.IRedisService;

@Service
public class UmsResourceCacheService implements IUmsResourceCacheService {

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Autowired
    private IRedisService redisService;

    @Override
    public Map<String, ConfigAttribute> getResourceMap() {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST;
        Map<Object, Object> map = redisService.hGetAll(key);
        Set<Object> hashKeySet = map.keySet();
        Map<String, ConfigAttribute> map2 = new ConcurrentHashMap<>();
        for(Object hashKey : hashKeySet) {
            map2.put((String)hashKey, (ConfigAttribute)map.get(hashKey));
        }
        return map2;
    }

    @Override
    public boolean setResourceMap(Map<String, ConfigAttribute> resourceMap) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST;
        redisService.remove(key);
        redisService.hSetAll(key, resourceMap);
        return true;
    }

    @Override
    public boolean putResourceMap(String hashKey, ConfigAttribute configAttribute) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST;
        redisService.hSet(key, hashKey, configAttribute);
        return true;
    }

    @Override
    public ConfigAttribute getResourceMap(String hashKey) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST;
        return (ConfigAttribute)redisService.hGet(key, hashKey);
    }
    
}
