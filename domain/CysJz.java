package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 精轧对象 cys_jz
 * 
 * @author yinjinlu
 * @date 2025-01-17
 */
public class CysJz extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 精轧工序id */
    private String finishrId;

    /** 精轧工序编码 */
    @Excel(name = "精轧工序编码")
    private String finishrCode;

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

    /** 精轧次数 */
    @Excel(name = "精轧次数")
    private Long finishrollCount;

    /** 精轧设备 */
    @Excel(name = "精轧设备")
    private String finishrollEquipment;

    /** 轧前重量 */
    @Excel(name = "轧前重量")
    private BigDecimal weightBeforeRoll;

    /** 轧后重量 */
    @Excel(name = "轧后重量")
    private BigDecimal weightAfterRoll;

    /** 轧前规格 */
    @Excel(name = "轧前规格")
    private BigDecimal speBeforeRoll;

    /** 轧后规格 */
    @Excel(name = "轧后规格")
    private BigDecimal speAfterRoll;

    /** 精轧时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "精轧时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date finishrTime;

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

    public void setFinishrId(String finishrId) 
    {
        this.finishrId = finishrId;
    }

    public String getFinishrId() 
    {
        return finishrId;
    }
    public void setFinishrCode(String finishrCode) 
    {
        this.finishrCode = finishrCode;
    }

    public String getFinishrCode() 
    {
        return finishrCode;
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
    public void setFinishrollCount(Long finishrollCount) 
    {
        this.finishrollCount = finishrollCount;
    }

    public Long getFinishrollCount() 
    {
        return finishrollCount;
    }
    public void setFinishrollEquipment(String finishrollEquipment) 
    {
        this.finishrollEquipment = finishrollEquipment;
    }

    public String getFinishrollEquipment() 
    {
        return finishrollEquipment;
    }
    public void setWeightBeforeRoll(BigDecimal weightBeforeRoll) 
    {
        this.weightBeforeRoll = weightBeforeRoll;
    }

    public BigDecimal getWeightBeforeRoll() 
    {
        return weightBeforeRoll;
    }
    public void setWeightAfterRoll(BigDecimal weightAfterRoll) 
    {
        this.weightAfterRoll = weightAfterRoll;
    }

    public BigDecimal getWeightAfterRoll() 
    {
        return weightAfterRoll;
    }
    public void setSpeBeforeRoll(BigDecimal speBeforeRoll) 
    {
        this.speBeforeRoll = speBeforeRoll;
    }

    public BigDecimal getSpeBeforeRoll() 
    {
        return speBeforeRoll;
    }
    public void setSpeAfterRoll(BigDecimal speAfterRoll) 
    {
        this.speAfterRoll = speAfterRoll;
    }

    public BigDecimal getSpeAfterRoll() 
    {
        return speAfterRoll;
    }
    public void setFinishrTime(Date finishrTime) 
    {
        this.finishrTime = finishrTime;
    }

    public Date getFinishrTime() 
    {
        return finishrTime;
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
            .append("finishrId", getFinishrId())
            .append("finishrCode", getFinishrCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("productStatus", getProductStatus())
            .append("productThickness", getProductThickness())
            .append("productWidth", getProductWidth())
            .append("finishrollCount", getFinishrollCount())
            .append("finishrollEquipment", getFinishrollEquipment())
            .append("weightBeforeRoll", getWeightBeforeRoll())
            .append("weightAfterRoll", getWeightAfterRoll())
            .append("speBeforeRoll", getSpeBeforeRoll())
            .append("speAfterRoll", getSpeAfterRoll())
            .append("finishrTime", getFinishrTime())
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
