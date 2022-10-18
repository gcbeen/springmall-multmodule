package com.gcbeen.springmallgenerator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户举报表
 * </p>
 *
 * @author mybatis plus generator
 * @since 2022-06-11
 */
@TableName("cms_member_report")
@ApiModel(value = "CmsMemberReport对象", description = "用户举报表")
public class CmsMemberReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("举报类型：0->商品评价；1->话题内容；2->用户评论")
    private Integer reportType;

    @ApiModelProperty("举报人")
    private String reportMemberName;

    private Date createTime;

    private String reportObject;

    @ApiModelProperty("举报状态：0->未处理；1->已处理")
    private Integer reportStatus;

    @ApiModelProperty("处理结果：0->无效；1->有效；2->恶意")
    private Integer handleStatus;

    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }
    public String getReportMemberName() {
        return reportMemberName;
    }

    public void setReportMemberName(String reportMemberName) {
        this.reportMemberName = reportMemberName;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getReportObject() {
        return reportObject;
    }

    public void setReportObject(String reportObject) {
        this.reportObject = reportObject;
    }
    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }
    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CmsMemberReport{" +
            "id=" + id +
            ", reportType=" + reportType +
            ", reportMemberName=" + reportMemberName +
            ", createTime=" + createTime +
            ", reportObject=" + reportObject +
            ", reportStatus=" + reportStatus +
            ", handleStatus=" + handleStatus +
            ", note=" + note +
        "}";
    }
}
