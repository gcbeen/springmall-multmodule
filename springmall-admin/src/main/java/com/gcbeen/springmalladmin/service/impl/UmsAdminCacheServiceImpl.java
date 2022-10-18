package com.gcbeen.springmalladmin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcbeen.springmalladmin.dao.UmsAdminRoleRelationDao;
import com.gcbeen.springmalladmin.service.IUmsAdminCacheService;
import com.gcbeen.springmalladmin.service.IUmsAdminService;
import com.gcbeen.springmallcommon.service.IRedisService;
import com.gcbeen.springmallgenerator.entity.UmsAdmin;
import com.gcbeen.springmallgenerator.entity.UmsAdminRoleRelation;
import com.gcbeen.springmallgenerator.entity.UmsResource;
import com.gcbeen.springmallgenerator.mapper.UmsAdminMapper;
import com.gcbeen.springmallgenerator.mapper.UmsAdminRoleRelationMapper;

import cn.hutool.core.collection.CollUtil;

/**
 * 后台用户缓存 Service 实现类
 * 
 */
@Service
public class UmsAdminCacheServiceImpl implements IUmsAdminCacheService {

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    // 用户缓存 key REDIS_DATABASE:REDIS_KEY_ADMIN:username
    // 资源缓存 key REDIS_DATABASE:REDIS_KEY_RESOURCE_LIST:adminId

    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = adminMapper.selectById(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.remove(key);
        }
    }

    @Override
    public void delAdmin(String username) {
        // UmsAdmin admin = adminMapper.selectById(username);
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        redisService.remove(key);
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.remove(key);
        
    }

    @Override
    public void delResourceListByRole(Long roleId) {
        QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsAdminRoleRelation::getId, roleId);
        List<UmsAdminRoleRelation> adminRoleRelationList = adminRoleRelationMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(adminRoleRelationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminRoleRelationList.stream()
                .map(relation -> keyPrefix + relation.getAdminId())
                .collect(Collectors.toList());
            redisService.remove(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(UmsAdminRoleRelation::getId, roleIds);
        List<UmsAdminRoleRelation> adminRoleRelationList = adminRoleRelationMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(adminRoleRelationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminRoleRelationList.stream()
                .map(relation -> keyPrefix + relation.getAdminId())
                .collect(Collectors.toList());
            redisService.remove(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> adminIdList = adminRoleRelationDao.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.remove(keys);
        }
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" +username;
        return (UmsAdmin)redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<UmsResource>)redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
        
    }
    
}
