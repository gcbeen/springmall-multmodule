package com.gcbeen.springmallgenerator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 优惠券和产品的关系表
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@TableName("sms_coupon_product_relation")
@ApiModel(value = "SmsCouponProductRelation对象", description = "优惠券和产品的关系表")
public class SmsCouponProductRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long couponId;

    private Long productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品编码")
    private String productSn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    @Override
    public String toString() {
        return "SmsCouponProductRelation{" +
            "id=" + id +
            ", couponId=" + couponId +
            ", productId=" + productId +
            ", productName=" + productName +
            ", productSn=" + productSn +
        "}";
    }
}
