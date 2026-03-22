package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 成品检验对象 cys_cpjy
 * 
 * @author yinjinlu
 * @date 2025-01-17
 */
public class CysCpjy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成品检验id */
    private String fpiId;

    /** 酸洗工序编码 */
    @Excel(name = "酸洗工序编码")
    private String fpiCode;

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

    /** 抗拉强度 */
    @Excel(name = "抗拉强度")
    private BigDecimal tensileStrength;

    /** 延伸率 */
    @Excel(name = "延伸率")
    private BigDecimal elongation;

    /** 硬度 */
    @Excel(name = "硬度")
    private BigDecimal hardness;

    /** 杯凸值 */
    @Excel(name = "杯凸值")
    private BigDecimal cupConvexValue;

    /** 导电率 */
    @Excel(name = "导电率")
    private BigDecimal electricalConductivity;

    /** 检验类型 */
    @Excel(name = "检验类型")
    private String inspectionType;

    /** 检验时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "检验时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date inspectionTime;

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

    public void setFpiId(String fpiId) 
    {
        this.fpiId = fpiId;
    }

    public String getFpiId() 
    {
        return fpiId;
    }
    public void setFpiCode(String fpiCode) 
    {
        this.fpiCode = fpiCode;
    }

    public String getFpiCode() 
    {
        return fpiCode;
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
    public void setTensileStrength(BigDecimal tensileStrength) 
    {
        this.tensileStrength = tensileStrength;
    }

    public BigDecimal getTensileStrength() 
    {
        return tensileStrength;
    }
    public void setElongation(BigDecimal elongation) 
    {
        this.elongation = elongation;
    }

    public BigDecimal getElongation() 
    {
        return elongation;
    }
    public void setHardness(BigDecimal hardness) 
    {
        this.hardness = hardness;
    }

    public BigDecimal getHardness() 
    {
        return hardness;
    }
    public void setCupConvexValue(BigDecimal cupConvexValue) 
    {
        this.cupConvexValue = cupConvexValue;
    }

    public BigDecimal getCupConvexValue() 
    {
        return cupConvexValue;
    }
    public void setElectricalConductivity(BigDecimal electricalConductivity) 
    {
        this.electricalConductivity = electricalConductivity;
    }

    public BigDecimal getElectricalConductivity() 
    {
        return electricalConductivity;
    }
    public void setInspectionType(String inspectionType) 
    {
        this.inspectionType = inspectionType;
    }

    public String getInspectionType() 
    {
        return inspectionType;
    }
    public void setInspectionTime(Date inspectionTime) 
    {
        this.inspectionTime = inspectionTime;
    }

    public Date getInspectionTime() 
    {
        return inspectionTime;
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
            .append("fpiId", getFpiId())
            .append("fpiCode", getFpiCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("productStatus", getProductStatus())
            .append("productThickness", getProductThickness())
            .append("productWidth", getProductWidth())
            .append("tensileStrength", getTensileStrength())
            .append("elongation", getElongation())
            .append("hardness", getHardness())
            .append("cupConvexValue", getCupConvexValue())
            .append("electricalConductivity", getElectricalConductivity())
            .append("inspectionType", getInspectionType())
            .append("inspectionTime", getInspectionTime())
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
