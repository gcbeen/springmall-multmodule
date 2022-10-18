package com.gcbeen.springmalladmin.service.impl;

import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcbeen.springmalladmin.service.IUmsResourceCacheService;
import com.gcbeen.springmalladmin.service.IUmsResourceService;
import com.gcbeen.springmallgenerator.entity.UmsResource;
import com.gcbeen.springmallgenerator.mapper.UmsResourceMapper;

@Service
public class UmsResourceService extends ServiceImpl<UmsResourceMapper, UmsResource> implements IUmsResourceService {

    private IUmsResourceCacheService resourceCacheService;

    @Override
    public boolean save(UmsResource resource) {
        boolean flag = super.save(resource);
        SecurityConfig configAttribute = new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName());
        resourceCacheService.putResourceMap(resource.getUrl(), configAttribute);
        return flag;
    }
}
