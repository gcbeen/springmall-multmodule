package com.gcbeen.springmalladmin.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ums_admin：后台用户表
// ums_role：后台用户角色表
// ums_permission：后台用户权限表
// ums_admin_role_relation：后台用户和角色关系表，用户与角色是多对多关系
// ums_role_permission_relation：后台用户角色和权限关系表，角色与权限是多对多关系
// ums_admin_permission_relation：后台用户和权限关系表
// (除角色中定义的权限以外的加减权限)，
// 加权限是指用户比角色多出的权限，
// 减权限是指用户比角色少的权限

@Deprecated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsAdmin implements Serializable {
    private Long id;
    private String username;
    private String password;
    
    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;

}
