package com.gcbeen.springmallgenerator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 会员统计信息
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@TableName("ums_member_statistics_info")
@ApiModel(value = "UmsMemberStatisticsInfo对象", description = "会员统计信息")
public class UmsMemberStatisticsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long memberId;

    @ApiModelProperty("累计消费金额")
    private BigDecimal consumeAmount;

    @ApiModelProperty("订单数量")
    private Integer orderCount;

    @ApiModelProperty("优惠券数量")
    private Integer couponCount;

    @ApiModelProperty("评价数")
    private Integer commentCount;

    @ApiModelProperty("退货数量")
    private Integer returnOrderCount;

    @ApiModelProperty("登录次数")
    private Integer loginCount;

    @ApiModelProperty("关注数量")
    private Integer attendCount;

    @ApiModelProperty("粉丝数量")
    private Integer fansCount;

    private Integer collectProductCount;

    private Integer collectSubjectCount;

    private Integer collectTopicCount;

    private Integer collectCommentCount;

    private Integer inviteFriendCount;

    @ApiModelProperty("最后一次下订单时间")
    private Date recentOrderTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }
    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
    public Integer getReturnOrderCount() {
        return returnOrderCount;
    }

    public void setReturnOrderCount(Integer returnOrderCount) {
        this.returnOrderCount = returnOrderCount;
    }
    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
    public Integer getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(Integer attendCount) {
        this.attendCount = attendCount;
    }
    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }
    public Integer getCollectProductCount() {
        return collectProductCount;
    }

    public void setCollectProductCount(Integer collectProductCount) {
        this.collectProductCount = collectProductCount;
    }
    public Integer getCollectSubjectCount() {
        return collectSubjectCount;
    }

    public void setCollectSubjectCount(Integer collectSubjectCount) {
        this.collectSubjectCount = collectSubjectCount;
    }
    public Integer getCollectTopicCount() {
        return collectTopicCount;
    }

    public void setCollectTopicCount(Integer collectTopicCount) {
        this.collectTopicCount = collectTopicCount;
    }
    public Integer getCollectCommentCount() {
        return collectCommentCount;
    }

    public void setCollectCommentCount(Integer collectCommentCount) {
        this.collectCommentCount = collectCommentCount;
    }
    public Integer getInviteFriendCount() {
        return inviteFriendCount;
    }

    public void setInviteFriendCount(Integer inviteFriendCount) {
        this.inviteFriendCount = inviteFriendCount;
    }
    public Date getRecentOrderTime() {
        return recentOrderTime;
    }

    public void setRecentOrderTime(Date recentOrderTime) {
        this.recentOrderTime = recentOrderTime;
    }

    @Override
    public String toString() {
        return "UmsMemberStatisticsInfo{" +
            "id=" + id +
            ", memberId=" + memberId +
            ", consumeAmount=" + consumeAmount +
            ", orderCount=" + orderCount +
            ", couponCount=" + couponCount +
            ", commentCount=" + commentCount +
            ", returnOrderCount=" + returnOrderCount +
            ", loginCount=" + loginCount +
            ", attendCount=" + attendCount +
            ", fansCount=" + fansCount +
            ", collectProductCount=" + collectProductCount +
            ", collectSubjectCount=" + collectSubjectCount +
            ", collectTopicCount=" + collectTopicCount +
            ", collectCommentCount=" + collectCommentCount +
            ", inviteFriendCount=" + inviteFriendCount +
            ", recentOrderTime=" + recentOrderTime +
        "}";
    }
}
