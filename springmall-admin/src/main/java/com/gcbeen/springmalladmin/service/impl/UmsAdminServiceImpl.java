package com.gcbeen.springmalladmin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcbeen.springmalladmin.dao.UmsAdminDao;
import com.gcbeen.springmalladmin.dao.UmsAdminRoleRelationDao;
import com.gcbeen.springmalladmin.dto.AdminUserDetails;
import com.gcbeen.springmalladmin.dto.UpdateAdminPasswordParam;
import com.gcbeen.springmalladmin.service.IUmsAdminCacheService;
import com.gcbeen.springmalladmin.service.IUmsAdminService;
import com.gcbeen.springmallcommon.exception.Asserts;
import com.gcbeen.springmallcommon.util.RequestUtil;
import com.gcbeen.springmallgenerator.entity.UmsAdmin;
import com.gcbeen.springmallgenerator.entity.UmsAdminLoginLog;
import com.gcbeen.springmallgenerator.entity.UmsAdminRoleRelation;
import com.gcbeen.springmallgenerator.entity.UmsPermission;
import com.gcbeen.springmallgenerator.entity.UmsResource;
import com.gcbeen.springmallgenerator.entity.UmsRole;
import com.gcbeen.springmallgenerator.mapper.UmsAdminLoginLogMapper;
import com.gcbeen.springmallgenerator.mapper.UmsAdminMapper;
import com.gcbeen.springmallgenerator.mapper.UmsAdminRoleRelationMapper;
// import com.gcbeen.springmallsecurity.util.JwtTokenUtil;
import com.gcbeen.springmallsecurity.util.JwtTokenUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * IUmsAdminService 实现类
 */
// @Transactional
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {
    // extends ServiceImpl<CmsHelpCategoryMapper, CmsHelpCategory> implements ICmsHelpCategoryService
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    
    // @Autowired
    // private UserDetailsService userDetailsService;

    // @Autowired
    // private AuthenticationManager authenticationManager;

    // private AbstractUserDetailsAuthenticationProvider abstractUserDetailsAuthenticationProvider;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminDao adminDao;

    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private IUmsAdminCacheService adminCacheService;

    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    
    @Override
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


    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
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

    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime( new Date() );
        umsAdmin.setStatus(1);
        // 查询 用户名是否存在
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsAdmin::getUsername, umsAdmin.getUsername());
        boolean hasUsername = adminMapper.exists(queryWrapper);
        if (hasUsername) {
            // 用户已经存在
            return null;
        }
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        // 密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if ( !passwordEncoder.matches(password, userDetails.getPassword()) ) {
                // throw new BadCredentialsException("密码不正确");
                Asserts.fail("密码不正确");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常: {}", e.getMessage());
        }
        return token;
    }

    public void logout(String token) {
        // 清除缓存
        // redisService.remove(key);
        String username = jwtTokenUtil.getUserNameFromToken(token);
        adminCacheService.delAdmin(username);
        // 清除上下文对象
        SecurityContextHolder.clearContext();
    }

    private void updateLoginTimeByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin == null) {
            return;
        }
        admin.setLoginTime( new Date() );
        adminMapper.updateById(admin);
    }


    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin == null) {
            return;
        }
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime( new Date() );
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(loginLog);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }



    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        // 查询 角色权限
        // QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>();
        // queryWrapper.lambda().eq(UmsAdminRoleRelation::getAdminId, adminId);
        // List<UmsAdminRoleRelation>  = adminRoleRelationMapper.selectList(queryWrapper);

        return adminDao.permissionList(adminId);
    }


    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        // 1. 删除 原来的 角色
        // List<UmsRole> roleList = adminRoleRelationMapper.getRoleList(adminId);
        // List<Long> oldRoleIds = roleList.stream().map(role -> role.getId()).collect(Collectors.toList());
        QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsAdminRoleRelation::getAdminId, adminId);
            // .in(UmsAdminRoleRelation::getRoleId, oldRoleIds);
        adminRoleRelationMapper.delete(queryWrapper);
        // 2. 建立 新的 角色
        if (!CollUtil.isNotEmpty(roleIds)) {
            List<UmsAdminRoleRelation> adminRoleRelationList = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation adminRoleRelation = new UmsAdminRoleRelation();
                adminRoleRelation.setAdminId(adminId);
                adminRoleRelation.setRoleId(roleId);
                adminRoleRelationList.add(adminRoleRelation);
            }
            adminRoleRelationDao.insertList(adminRoleRelationList);
        }
        adminCacheService.delResourceList(adminId);
        return count;
    }


    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }


    @Override
    public int updatePassword(UpdateAdminPasswordParam updateParam) {
        String username = updateParam.getUsername();
        String oldPassword = updateParam.getOldPassword();
        String newPassword = updateParam.getNewPassword();
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(oldPassword) || StrUtil.isEmpty(newPassword)) {
            // 参数错误
            return -1;
        }
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsAdmin::getUsername, username);
        UmsAdmin admin = adminMapper.selectOne(queryWrapper);
        if ( admin == null ) {
            // 用户不存在
            return -2;
        }
        // if (passwordEncoder.matches(oldPassword, admin.getPassword())) {
        //     // 旧密码错误
        //     return -3;
        // }
        // admin.setPassword(passwordEncoder.encode(newPassword));
        adminMapper.updateById(admin);
        adminCacheService.delAdmin(admin.getId());
        return 1;
    }
    
}
