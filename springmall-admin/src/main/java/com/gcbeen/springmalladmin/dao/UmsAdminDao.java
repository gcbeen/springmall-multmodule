package com.gcbeen.springmalladmin.dao;

import java.util.List;

import com.gcbeen.springmallgenerator.entity.UmsPermission;

public interface UmsAdminDao {
    public List<UmsPermission> permissionList(Long adminId);
}
