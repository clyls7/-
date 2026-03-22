package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 酸洗对象 cys_sx
 * 
 * @author yinjinlu
 * @date 2025-01-17
 */
public class CysSx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 酸洗工序id */
    private String picklingId;

    /** 酸洗工序编码 */
    @Excel(name = "酸洗工序编码")
    private String picklingCode;

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

    /** 酸洗次数 */
    @Excel(name = "酸洗次数")
    private Long picklingCount;

    /** 来料情况 */
    @Excel(name = "来料情况")
    private String incomingMaterials;

    /** 清洗设备 */
    @Excel(name = "清洗设备")
    private String cleaningEquipment;

    /** 清洗后重量 */
    @Excel(name = "清洗后重量")
    private BigDecimal weightAfterCleaning;

    /** 清洗后厚度 */
    @Excel(name = "清洗后厚度")
    private BigDecimal thicknessAfterCleaning;

    /** 清洗后宽度 */
    @Excel(name = "清洗后宽度")
    private BigDecimal widthAfterCleaning;

    /** 清洗时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "清洗时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date picklingTime;

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

    public void setPicklingId(String picklingId) 
    {
        this.picklingId = picklingId;
    }

    public String getPicklingId() 
    {
        return picklingId;
    }
    public void setPicklingCode(String picklingCode) 
    {
        this.picklingCode = picklingCode;
    }

    public String getPicklingCode() 
    {
        return picklingCode;
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
    public void setPicklingCount(Long picklingCount) 
    {
        this.picklingCount = picklingCount;
    }

    public Long getPicklingCount() 
    {
        return picklingCount;
    }
    public void setIncomingMaterials(String incomingMaterials) 
    {
        this.incomingMaterials = incomingMaterials;
    }

    public String getIncomingMaterials() 
    {
        return incomingMaterials;
    }
    public void setCleaningEquipment(String cleaningEquipment) 
    {
        this.cleaningEquipment = cleaningEquipment;
    }

    public String getCleaningEquipment() 
    {
        return cleaningEquipment;
    }
    public void setWeightAfterCleaning(BigDecimal weightAfterCleaning) 
    {
        this.weightAfterCleaning = weightAfterCleaning;
    }

    public BigDecimal getWeightAfterCleaning() 
    {
        return weightAfterCleaning;
    }
    public void setThicknessAfterCleaning(BigDecimal thicknessAfterCleaning) 
    {
        this.thicknessAfterCleaning = thicknessAfterCleaning;
    }

    public BigDecimal getThicknessAfterCleaning() 
    {
        return thicknessAfterCleaning;
    }
    public void setWidthAfterCleaning(BigDecimal widthAfterCleaning) 
    {
        this.widthAfterCleaning = widthAfterCleaning;
    }

    public BigDecimal getWidthAfterCleaning() 
    {
        return widthAfterCleaning;
    }
    public void setPicklingTime(Date picklingTime) 
    {
        this.picklingTime = picklingTime;
    }

    public Date getPicklingTime() 
    {
        return picklingTime;
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
            .append("picklingId", getPicklingId())
            .append("picklingCode", getPicklingCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("productStatus", getProductStatus())
            .append("productThickness", getProductThickness())
            .append("productWidth", getProductWidth())
            .append("picklingCount", getPicklingCount())
            .append("incomingMaterials", getIncomingMaterials())
            .append("cleaningEquipment", getCleaningEquipment())
            .append("weightAfterCleaning", getWeightAfterCleaning())
            .append("thicknessAfterCleaning", getThicknessAfterCleaning())
            .append("widthAfterCleaning", getWidthAfterCleaning())
            .append("picklingTime", getPicklingTime())
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
