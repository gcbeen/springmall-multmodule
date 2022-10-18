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
 * 话题表
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@TableName("cms_topic")
@ApiModel(value = "CmsTopic对象", description = "话题表")
public class CmsTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long categoryId;

    private String name;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    @ApiModelProperty("参与人数")
    private Integer attendCount;

    @ApiModelProperty("关注人数")
    private Integer attentionCount;

    private Integer readCount;

    @ApiModelProperty("奖品名称")
    private String awardName;

    @ApiModelProperty("参与方式")
    private String attendType;

    @ApiModelProperty("话题内容")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    public Integer getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(Integer attendCount) {
        this.attendCount = attendCount;
    }
    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }
    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }
    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
    public String getAttendType() {
        return attendType;
    }

    public void setAttendType(String attendType) {
        this.attendType = attendType;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CmsTopic{" +
            "id=" + id +
            ", categoryId=" + categoryId +
            ", name=" + name +
            ", createTime=" + createTime +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", attendCount=" + attendCount +
            ", attentionCount=" + attentionCount +
            ", readCount=" + readCount +
            ", awardName=" + awardName +
            ", attendType=" + attendType +
            ", content=" + content +
        "}";
    }
}
