package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 铣面对象 cys_smx
 * 
 * @author yinjinlu
 * @date 2025-04-30
 */
public class CysSmx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 双面铣工序id */
    private String doublesidedId;

    /** 双面铣工序编码 */
    @Excel(name = "双面铣工序编码")
    private String dsmCode;

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

    /** 铣面次数 */
    @Excel(name = "铣面次数")
    private Long millingCount;

    /** 铣前规格 */
    @Excel(name = "铣前规格")
    private BigDecimal spBeforeMilling;

    /** 铣后规格 */
    @Excel(name = "铣后规格")
    private BigDecimal spAfterMilling;

    /** 铣前重量 */
    @Excel(name = "铣前重量")
    private BigDecimal spBeforeWeight;

    /** 铣后重量 */
    @Excel(name = "铣后重量")
    private BigDecimal spAfterWeight;

    /** 铣面时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "铣面时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date millingTime;

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

    public void setDoublesidedId(String doublesidedId) 
    {
        this.doublesidedId = doublesidedId;
    }

    public String getDoublesidedId() 
    {
        return doublesidedId;
    }
    public void setDsmCode(String dsmCode) 
    {
        this.dsmCode = dsmCode;
    }

    public String getDsmCode() 
    {
        return dsmCode;
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
    public void setMillingCount(Long millingCount) 
    {
        this.millingCount = millingCount;
    }

    public Long getMillingCount() 
    {
        return millingCount;
    }
    public void setSpBeforeMilling(BigDecimal spBeforeMilling) 
    {
        this.spBeforeMilling = spBeforeMilling;
    }

    public BigDecimal getSpBeforeMilling() 
    {
        return spBeforeMilling;
    }
    public void setSpAfterMilling(BigDecimal spAfterMilling) 
    {
        this.spAfterMilling = spAfterMilling;
    }

    public BigDecimal getSpAfterMilling() 
    {
        return spAfterMilling;
    }
    public void setSpBeforeWeight(BigDecimal spBeforeWeight) 
    {
        this.spBeforeWeight = spBeforeWeight;
    }

    public BigDecimal getSpBeforeWeight() 
    {
        return spBeforeWeight;
    }
    public void setSpAfterWeight(BigDecimal spAfterWeight) 
    {
        this.spAfterWeight = spAfterWeight;
    }

    public BigDecimal getSpAfterWeight() 
    {
        return spAfterWeight;
    }
    public void setMillingTime(Date millingTime) 
    {
        this.millingTime = millingTime;
    }

    public Date getMillingTime() 
    {
        return millingTime;
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
            .append("doublesidedId", getDoublesidedId())
            .append("dsmCode", getDsmCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("millingCount", getMillingCount())
            .append("spBeforeMilling", getSpBeforeMilling())
            .append("spAfterMilling", getSpAfterMilling())
            .append("spBeforeWeight", getSpBeforeWeight())
            .append("spAfterWeight", getSpAfterWeight())
            .append("millingTime", getMillingTime())
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
