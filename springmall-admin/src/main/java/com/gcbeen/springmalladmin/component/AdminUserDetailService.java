package com.gcbeen.springmalladmin.component;

import java.util.List;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcbeen.springmalladmin.dao.UmsAdminDao;
import com.gcbeen.springmalladmin.dao.UmsAdminRoleRelationDao;
import com.gcbeen.springmalladmin.dto.AdminUserDetails;
import com.gcbeen.springmalladmin.service.IUmsAdminCacheService;
import com.gcbeen.springmallgenerator.entity.UmsAdmin;
import com.gcbeen.springmallgenerator.entity.UmsPermission;
import com.gcbeen.springmallgenerator.entity.UmsResource;
import com.gcbeen.springmallgenerator.entity.UmsRole;
import com.gcbeen.springmallgenerator.mapper.UmsAdminMapper;

import cn.hutool.core.collection.CollUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AdminUserDetailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserDetailService.class);

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminDao adminDao;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private IUmsAdminCacheService adminCacheService;
    
    
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if (admin != null) {
            return admin;
        }
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsAdmin::getUsername, username);
        admin = adminMapper.selectOne(queryWrapper);
        if (admin != null) {
            adminCacheService.setAdmin(admin);
        }
        return admin;
    }


    // 将我们查询出来的用户信息和权限信息组装成一个UserDetails返回
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            return  resourceList;
        }
        resourceList = adminRoleRelationDao.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            adminCacheService.setResourceList(adminId,resourceList);
        }
        return resourceList;
    }
    
    public List<UmsPermission> getPermissionList(Long adminId) {
        // 查询 角色权限
        // QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>();
        // queryWrapper.lambda().eq(UmsAdminRoleRelation::getAdminId, adminId);
        // List<UmsAdminRoleRelation>  = adminRoleRelationMapper.selectList(queryWrapper);

        return adminDao.permissionList(adminId);
    }

    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }
    
}
