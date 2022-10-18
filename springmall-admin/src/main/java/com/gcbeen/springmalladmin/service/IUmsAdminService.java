package com.gcbeen.springmalladmin.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gcbeen.springmalladmin.dto.UpdateAdminPasswordParam;
import com.gcbeen.springmallgenerator.entity.UmsAdmin;
import com.gcbeen.springmallgenerator.entity.UmsPermission;
import com.gcbeen.springmallgenerator.entity.UmsResource;
import com.gcbeen.springmallgenerator.entity.UmsRole;

/**
 * 后台管理员 Service
 */
public interface IUmsAdminService extends IService<UmsAdmin> {
    // extends IService<UmsAdmin>
    
    /**
     * 根据用户名获取后台管理员
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     * @param umsAdminParam
     * @return
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限 和 单独+-权限
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 刷新 token
     * @param oldToken
     * @return
     */
    String refreshToken(String oldToken);

    /**
     * 修改 后台用户 的角色
     * @param adminId
     * @param roleIds
     * @return
     */
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对应角色
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 修改密码
     * @param updatePasswordParam
     * @return
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);



    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

}
