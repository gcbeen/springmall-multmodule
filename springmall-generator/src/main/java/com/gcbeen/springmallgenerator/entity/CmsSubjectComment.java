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
 * 专题评论表
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@TableName("cms_subject_comment")
@ApiModel(value = "CmsSubjectComment对象", description = "专题评论表")
public class CmsSubjectComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long subjectId;

    private String memberNickName;

    private String memberIcon;

    private String content;

    private Date createTime;

    private Integer showStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }
    public String getMemberIcon() {
        return memberIcon;
    }

    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return "CmsSubjectComment{" +
            "id=" + id +
            ", subjectId=" + subjectId +
            ", memberNickName=" + memberNickName +
            ", memberIcon=" + memberIcon +
            ", content=" + content +
            ", createTime=" + createTime +
            ", showStatus=" + showStatus +
        "}";
    }
}
