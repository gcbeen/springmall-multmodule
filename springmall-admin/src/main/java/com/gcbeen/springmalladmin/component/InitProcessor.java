package com.gcbeen.springmalladmin.component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;

import com.gcbeen.springmalladmin.service.IUmsResourceCacheService;
import com.gcbeen.springmalladmin.service.IUmsResourceService;
import com.gcbeen.springmallgenerator.entity.UmsResource;

@Component
public class InitProcessor {

    @Autowired
    private IUmsResourceService resourceService;

    @Autowired
    private IUmsResourceCacheService resourceCacheService;

    // 在系统启动的时候就把 URI权限数据 都放到 缓存中
    // URI权限数据 改变时手动刷新
    @PostConstruct
    public void init() {
        Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        List<UmsResource> resourceList = resourceService.list();
        for (UmsResource resource : resourceList) { 
            map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
        }
        resourceCacheService.setResourceMap(map);
    }

}
