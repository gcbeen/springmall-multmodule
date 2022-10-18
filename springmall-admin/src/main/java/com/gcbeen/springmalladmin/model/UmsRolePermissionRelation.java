package com.gcbeen.springmalladmin.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Deprecated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsRolePermissionRelation implements Serializable {
    private Long id;

    private Long roleId;

    private Long permissionId;
}
