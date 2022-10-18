package com.gcbeen.springmalladmin.model;

import java.io.Serializable;

// import com.baomidou.mybatisplus.annotation.TableName;

// import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Deprecated
@Data
@NoArgsConstructor
@AllArgsConstructor
// @TableName(value = "pms_product")
public class PmsProduct implements Serializable {
    @ApiModelProperty(value = "产品 ID")
    private Integer id;

    @ApiModelProperty(value = "品牌 ID")
    private Integer brandId;

    @ApiModelProperty(value = "商品名称")
    private String name;

}
