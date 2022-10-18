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
 * 限时购通知记录
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@TableName("sms_flash_promotion_log")
@ApiModel(value = "SmsFlashPromotionLog对象", description = "限时购通知记录")
public class SmsFlashPromotionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer memberId;

    private Long productId;

    private String memberPhone;

    private String productName;

    @ApiModelProperty("会员订阅时间")
    private Date subscribeTime;

    private Date sendTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "SmsFlashPromotionLog{" +
            "id=" + id +
            ", memberId=" + memberId +
            ", productId=" + productId +
            ", memberPhone=" + memberPhone +
            ", productName=" + productName +
            ", subscribeTime=" + subscribeTime +
            ", sendTime=" + sendTime +
        "}";
    }
}
