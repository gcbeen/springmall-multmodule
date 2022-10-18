package com.gcbeen.springmalladmin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gcbeen.springmallgenerator.entity.UmsAdminRoleRelation;
import com.gcbeen.springmallgenerator.entity.UmsResource;
import com.gcbeen.springmallgenerator.entity.UmsRole;

/**
 * 后台用户与角色关系管理 自定义 DAO
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 批量插入 后台用户角色关系
     * @param adminRoleRelationList
     * @return
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取 后台用户所有角色
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取 后台用户 所有可访问资源
     * @param adminId
     * @return
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取 拥有资源权限的 后台用户ID列表
     * @param resourceId
     * @return
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);


}
