package com.gcbeen.springmalladmin.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Deprecated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsRole implements Serializable {
    
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "后台用户数量")
    private Integer adminCount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;

    private Integer sort;

}
