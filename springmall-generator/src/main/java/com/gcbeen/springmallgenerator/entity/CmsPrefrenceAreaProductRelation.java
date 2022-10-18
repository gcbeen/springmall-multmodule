package com.gcbeen.springmallgenerator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 优选专区和产品关系表
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@TableName("cms_prefrence_area_product_relation")
@ApiModel(value = "CmsPrefrenceAreaProductRelation对象", description = "优选专区和产品关系表")
public class CmsPrefrenceAreaProductRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long prefrenceAreaId;

    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getPrefrenceAreaId() {
        return prefrenceAreaId;
    }

    public void setPrefrenceAreaId(Long prefrenceAreaId) {
        this.prefrenceAreaId = prefrenceAreaId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CmsPrefrenceAreaProductRelation{" +
            "id=" + id +
            ", prefrenceAreaId=" + prefrenceAreaId +
            ", productId=" + productId +
        "}";
    }
}
