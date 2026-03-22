package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 精剪边对象 cys_jjb
 * 
 * @author yinjinlu
 * @date 2025-05-05
 */
public class CysJjb extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 精剪边工序id */
    private String trimId;

    /** 精剪边工序编码 */
    @Excel(name = "精剪边工序编码")
    private String trimCode;

    /** 生产工单id */
    @Excel(name = "生产工单id")
    private Long workorderId;

    /** 生产工单编码 */
    @Excel(name = "生产工单编码")
    private String workorderCode;

    /** 牌号 */
    @Excel(name = "牌号")
    private String brand;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNumber;

    /** 产品状态 */
    @Excel(name = "产品状态")
    private String productStatus;

    /** 产品厚度 */
    @Excel(name = "产品厚度")
    private BigDecimal productThickness;

    /** 产品宽度 */
    @Excel(name = "产品宽度")
    private BigDecimal productWidth;

    /** 剪边前重量 */
    @Excel(name = "剪边前重量")
    private BigDecimal weightBeforeTrim;

    /** 剪边后重量 */
    @Excel(name = "剪边后重量")
    private BigDecimal weightAfterTrim;

    /** 剪边前宽度 */
    @Excel(name = "剪边前宽度")
    private BigDecimal widthBeforeTrim;

    /** 剪边后宽度 */
    @Excel(name = "剪边后宽度")
    private BigDecimal widthAfterTrim;

    /** 剪边时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "剪边时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date trimTime;

    /** 员工id */
    @Excel(name = "员工id")
    private Long userId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String userName;

    /** 质量判定 */
    @Excel(name = "质量判定")
    private String qualityJudgment;

    /** 异常原因 */
    @Excel(name = "异常原因")
    private String abnormalCause;

    public void setTrimId(String trimId) 
    {
        this.trimId = trimId;
    }

    public String getTrimId() 
    {
        return trimId;
    }
    public void setTrimCode(String trimCode) 
    {
        this.trimCode = trimCode;
    }

    public String getTrimCode() 
    {
        return trimCode;
    }
    public void setWorkorderId(Long workorderId) 
    {
        this.workorderId = workorderId;
    }

    public Long getWorkorderId() 
    {
        return workorderId;
    }
    public void setWorkorderCode(String workorderCode) 
    {
        this.workorderCode = workorderCode;
    }

    public String getWorkorderCode() 
    {
        return workorderCode;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setBatchNumber(String batchNumber) 
    {
        this.batchNumber = batchNumber;
    }

    public String getBatchNumber() 
    {
        return batchNumber;
    }
    public void setProductStatus(String productStatus) 
    {
        this.productStatus = productStatus;
    }

    public String getProductStatus() 
    {
        return productStatus;
    }
    public void setProductThickness(BigDecimal productThickness) 
    {
        this.productThickness = productThickness;
    }

    public BigDecimal getProductThickness() 
    {
        return productThickness;
    }
    public void setProductWidth(BigDecimal productWidth) 
    {
        this.productWidth = productWidth;
    }

    public BigDecimal getProductWidth() 
    {
        return productWidth;
    }
    public void setWeightBeforeTrim(BigDecimal weightBeforeTrim) 
    {
        this.weightBeforeTrim = weightBeforeTrim;
    }

    public BigDecimal getWeightBeforeTrim() 
    {
        return weightBeforeTrim;
    }
    public void setWeightAfterTrim(BigDecimal weightAfterTrim) 
    {
        this.weightAfterTrim = weightAfterTrim;
    }

    public BigDecimal getWeightAfterTrim() 
    {
        return weightAfterTrim;
    }
    public void setWidthBeforeTrim(BigDecimal widthBeforeTrim) 
    {
        this.widthBeforeTrim = widthBeforeTrim;
    }

    public BigDecimal getWidthBeforeTrim() 
    {
        return widthBeforeTrim;
    }
    public void setWidthAfterTrim(BigDecimal widthAfterTrim) 
    {
        this.widthAfterTrim = widthAfterTrim;
    }

    public BigDecimal getWidthAfterTrim() 
    {
        return widthAfterTrim;
    }
    public void setTrimTime(Date trimTime) 
    {
        this.trimTime = trimTime;
    }

    public Date getTrimTime() 
    {
        return trimTime;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setQualityJudgment(String qualityJudgment) 
    {
        this.qualityJudgment = qualityJudgment;
    }

    public String getQualityJudgment() 
    {
        return qualityJudgment;
    }
    public void setAbnormalCause(String abnormalCause) 
    {
        this.abnormalCause = abnormalCause;
    }

    public String getAbnormalCause() 
    {
        return abnormalCause;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("trimId", getTrimId())
            .append("trimCode", getTrimCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("productStatus", getProductStatus())
            .append("productThickness", getProductThickness())
            .append("productWidth", getProductWidth())
            .append("weightBeforeTrim", getWeightBeforeTrim())
            .append("weightAfterTrim", getWeightAfterTrim())
            .append("widthBeforeTrim", getWidthBeforeTrim())
            .append("widthAfterTrim", getWidthAfterTrim())
            .append("trimTime", getTrimTime())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("qualityJudgment", getQualityJudgment())
            .append("abnormalCause", getAbnormalCause())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
