package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 锯切对象 cys_jq
 * 
 * @author yinjinlu
 * @date 2025-03-24
 */
public class CysJq extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 锯切工序id */
    private String sawingId;

    /** 锯切工序编码 */
    @Excel(name = "锯切工序编码")
    private String sawingCode;

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

    /** 拉铸日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "拉铸日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date castingData;

    /** 锯切前重量 */
    @Excel(name = "锯切前重量")
    private BigDecimal beforeSawing;

    /** 锯切后重量 */
    @Excel(name = "锯切后重量")
    private BigDecimal afterSawing;

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

    public void setSawingId(String sawingId) 
    {
        this.sawingId = sawingId;
    }

    public String getSawingId() 
    {
        return sawingId;
    }
    public void setSawingCode(String sawingCode) 
    {
        this.sawingCode = sawingCode;
    }

    public String getSawingCode() 
    {
        return sawingCode;
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
    public void setCastingData(Date castingData) 
    {
        this.castingData = castingData;
    }

    public Date getCastingData() 
    {
        return castingData;
    }
    public void setBeforeSawing(BigDecimal beforeSawing) 
    {
        this.beforeSawing = beforeSawing;
    }

    public BigDecimal getBeforeSawing() 
    {
        return beforeSawing;
    }
    public void setAfterSawing(BigDecimal afterSawing) 
    {
        this.afterSawing = afterSawing;
    }

    public BigDecimal getAfterSawing() 
    {
        return afterSawing;
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
            .append("sawingId", getSawingId())
            .append("sawingCode", getSawingCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("castingData", getCastingData())
            .append("beforeSawing", getBeforeSawing())
            .append("afterSawing", getAfterSawing())
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
