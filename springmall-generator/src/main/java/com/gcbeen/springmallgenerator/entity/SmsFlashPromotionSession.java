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
 * 限时购场次表
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@TableName("sms_flash_promotion_session")
@ApiModel(value = "SmsFlashPromotionSession对象", description = "限时购场次表")
public class SmsFlashPromotionSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("场次名称")
    private String name;

    @ApiModelProperty("每日开始时间")
    private Date startTime;

    @ApiModelProperty("每日结束时间")
    private Date endTime;

    @ApiModelProperty("启用状态：0->不启用；1->启用")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SmsFlashPromotionSession{" +
            "id=" + id +
            ", name=" + name +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", status=" + status +
            ", createTime=" + createTime +
        "}";
    }
}
