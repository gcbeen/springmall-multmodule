package com.gcbeen.springmallgenerator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 优惠券使用、领取历史表
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@TableName("sms_coupon_history")
@ApiModel(value = "SmsCouponHistory对象", description = "优惠券使用、领取历史表")
public class SmsCouponHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long couponId;

    private Long memberId;

    private String couponCode;

    @ApiModelProperty("领取人昵称")
    private String memberNickname;

    @ApiModelProperty("获取类型：0->后台赠送；1->主动获取")
    private Integer getType;

    private Date createTime;

    @ApiModelProperty("使用状态：0->未使用；1->已使用；2->已过期")
    private Integer useStatus;

    @ApiModelProperty("使用时间")
    private Date useTime;

    @ApiModelProperty("订单编号")
    private Long orderId;

    @ApiModelProperty("订单号码")
    private String orderSn;

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
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }
    public Integer getGetType() {
        return getType;
    }

    public void setGetType(Integer getType) {
        this.getType = getType;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    @Override
    public String toString() {
        return "SmsCouponHistory{" +
            "id=" + id +
            ", couponId=" + couponId +
            ", memberId=" + memberId +
            ", couponCode=" + couponCode +
            ", memberNickname=" + memberNickname +
            ", getType=" + getType +
            ", createTime=" + createTime +
            ", useStatus=" + useStatus +
            ", useTime=" + useTime +
            ", orderId=" + orderId +
            ", orderSn=" + orderSn +
        "}";
    }
}
