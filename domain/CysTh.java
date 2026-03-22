package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 退火对象 cys_th
 * 
 * @author yinjinlu
 * @date 2025-01-16
 */
public class CysTh extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 退火工序id */
    private String annealingId;

    /** 开坯工序编码 */
    @Excel(name = "开坯工序编码")
    private String annealingCode;

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

    /** 退火次数 */
    @Excel(name = "退火次数")
    private Long annealingCount;

    /** 炉号 */
    @Excel(name = "炉号")
    private String furnaceNo;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 规格 */
    @Excel(name = "规格")
    private BigDecimal specification;

    /** 加热温度 */
    @Excel(name = "加热温度")
    private BigDecimal heatingTemperature;

    /** 升温时间 */
    @Excel(name = "升温时间")
    private BigDecimal heatingTime;

    /** 保温时间 */
    @Excel(name = "保温时间")
    private BigDecimal insulationTime;

    /** 重量 */
    @Excel(name = "重量")
    private BigDecimal weight;

    /** 退火时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "退火时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date annealingTime;

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

    public void setAnnealingId(String annealingId) 
    {
        this.annealingId = annealingId;
    }

    public String getAnnealingId() 
    {
        return annealingId;
    }
    public void setAnnealingCode(String annealingCode) 
    {
        this.annealingCode = annealingCode;
    }

    public String getAnnealingCode() 
    {
        return annealingCode;
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
    public void setAnnealingCount(Long annealingCount) 
    {
        this.annealingCount = annealingCount;
    }

    public Long getAnnealingCount() 
    {
        return annealingCount;
    }
    public void setFurnaceNo(String furnaceNo) 
    {
        this.furnaceNo = furnaceNo;
    }

    public String getFurnaceNo() 
    {
        return furnaceNo;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setSpecification(BigDecimal specification) 
    {
        this.specification = specification;
    }

    public BigDecimal getSpecification() 
    {
        return specification;
    }
    public void setHeatingTemperature(BigDecimal heatingTemperature) 
    {
        this.heatingTemperature = heatingTemperature;
    }

    public BigDecimal getHeatingTemperature() 
    {
        return heatingTemperature;
    }
    public void setHeatingTime(BigDecimal heatingTime) 
    {
        this.heatingTime = heatingTime;
    }

    public BigDecimal getHeatingTime() 
    {
        return heatingTime;
    }
    public void setInsulationTime(BigDecimal insulationTime) 
    {
        this.insulationTime = insulationTime;
    }

    public BigDecimal getInsulationTime() 
    {
        return insulationTime;
    }
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }
    public void setAnnealingTime(Date annealingTime) 
    {
        this.annealingTime = annealingTime;
    }

    public Date getAnnealingTime() 
    {
        return annealingTime;
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
            .append("annealingId", getAnnealingId())
            .append("annealingCode", getAnnealingCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("productStatus", getProductStatus())
            .append("productThickness", getProductThickness())
            .append("productWidth", getProductWidth())
            .append("annealingCount", getAnnealingCount())
            .append("furnaceNo", getFurnaceNo())
            .append("location", getLocation())
            .append("specification", getSpecification())
            .append("heatingTemperature", getHeatingTemperature())
            .append("heatingTime", getHeatingTime())
            .append("insulationTime", getInsulationTime())
            .append("weight", getWeight())
            .append("annealingTime", getAnnealingTime())
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
