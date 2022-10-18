package com.gcbeen.springmalladmin.dto;


import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsAdminLoginParam {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message="用户名不能为空")
    private String username;

    @ApiModelProperty(value = "秘密", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
}
