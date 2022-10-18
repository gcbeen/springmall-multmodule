package com.gcbeen.springmalladmin.service;

import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

public interface IUmsResourceCacheService {
    
    /**
     * 获取 资源列表 的缓存信息
     * @return
     */
    Map<String, ConfigAttribute> getResourceMap();

    /**
     * 设置 资源列表 的缓存
     * @param resourceList
     */
    boolean setResourceMap(Map<String, ConfigAttribute> resourceMap);

    /**
     * 设置 资源
     * @param hashKey
     * @param configAttribute
     * @return
     */
    boolean putResourceMap(String hashKey, ConfigAttribute configAttribute);


    /**
     * 获取 资源
     * @param hashKey
     * @return
     */
    ConfigAttribute getResourceMap(String hashKey); 


}
