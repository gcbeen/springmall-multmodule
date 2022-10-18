package com.gcbeen.springmalladmin.service;

import java.util.List;

import com.gcbeen.springmallgenerator.entity.UmsAdmin;
import com.gcbeen.springmallgenerator.entity.UmsResource;

/**
 * 后台用户缓存 Service
 */
public interface IUmsAdminCacheService {
    /**
     * 删除后台用户 的缓存信息
     * @param adminId
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户 的缓存信息
     * @param username
     */
    void delAdmin(String username);

    /**
     * 删除后台用户的资源列表 的缓存信息
     * @param adminId
     */
    void delResourceList(Long adminId);

    /**
     * 当角色相关的资源信息改变时
     * 删除相关后台用户的资源列表 的缓存信息
     * @param roleId
     */
    void delResourceListByRole(Long roleId);

    /**
     * 
     * @param roleIds
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时
     * 删除 资源相关的后台用户的资源列表 的缓存信息
     * @param resourceId
     */
    void delResourceListByResource(Long resourceId);

    /**
     * 获取后台用户的 缓存信息
     * @param username
     * @return
     */
    UmsAdmin getAdmin(String username);

    /**
     * 设置后台用户 的缓存信息
     * @param admin
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 获取后台用户的资源列表 的缓存信息
     * @param adminId
     * @return
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置 后台用户的资源列表 的缓存
     * @param adminId
     * @param resourceList
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);

}
