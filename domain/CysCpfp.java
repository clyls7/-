package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 产品分配对象 cys_cpfp
 * 
 * @author yinjinlu
 * @date 2025-09-08
 */
public class CysCpfp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品分配id */
    private String paId;

    /** 产品分配编码 */
    @Excel(name = "产品分配编码")
    private String paCode;

    /** 生产订单id */
    @Excel(name = "生产订单id")
    private Long workorderId;

    /** 生产订单编码 */
    @Excel(name = "生产订单编码")
    private String workorderCode;

    /** 牌号 */
    @Excel(name = "牌号")
    private String brand;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNumber;

    /** 重量 */
    @Excel(name = "重量")
    private BigDecimal weight;

    /** 开坯厚度 */
    @Excel(name = "开坯厚度")
    private BigDecimal blankThickness;

    /** 开坯宽度 */
    @Excel(name = "开坯宽度")
    private BigDecimal blankWidth;

    /** 产品厚度 */
    @Excel(name = "产品厚度")
    private BigDecimal productThickness;

    /** 产品宽度 */
    @Excel(name = "产品宽度")
    private BigDecimal productWidth;

    /** 产品状态 */
    @Excel(name = "产品状态")
    private String productStatus;

    /** 产品物料编码 */
    @Excel(name = "产品物料编码")
    private String materialCode;

    /** 产品物料名称 */
    @Excel(name = "产品物料名称")
    private String materialName;

    /** 公差类型 */
    @Excel(name = "公差类型")
    private String toleranceType;

    /** 公差要求 */
    @Excel(name = "公差要求")
    private BigDecimal toleranceCount;

    /** 抗拉强度 */
    @Excel(name = "抗拉强度")
    private String tensileStrength;

    /** 抗拉强度起始值 */
    @Excel(name = "抗拉强度起始值")
    private BigDecimal tensileStrengthStart;

    /** 抗拉强度结束值 */
    @Excel(name = "抗拉强度结束值")
    private BigDecimal tensileStrengthEnd;

    /** 硬度 */
    @Excel(name = "硬度")
    private String hardness;

    /** 硬度起始值 */
    @Excel(name = "硬度起始值")
    private BigDecimal hardnessStart;

    /** 硬度结束值 */
    @Excel(name = "硬度结束值")
    private BigDecimal hardnessEnd;

    /** 需求数量 */
    @Excel(name = "需求数量")
    private Long needCount;

    /** 已分配数量 */
    @Excel(name = "已分配数量")
    private Long allocatedCount;

    /** 已完成数量 */
    @Excel(name = "已完成数量")
    private Long completedCount;

    /** 执行中数量 */
    @Excel(name = "执行中数量")
    private Long progressCount;

    /** 报废/改制数量 */
    @Excel(name = "报废/改制数量")
    private Long sorrCount;

    /** 产品分配状态 */
    @Excel(name = "产品分配状态")
    private String paStatus;

    /** 工单类型 */
    @Excel(name = "工单类型")
    private String workorderType;

    /** 员工id */
    @Excel(name = "员工id")
    private Long userId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String userName;

    public void setPaId(String paId) 
    {
        this.paId = paId;
    }

    public String getPaId() 
    {
        return paId;
    }
    public void setPaCode(String paCode) 
    {
        this.paCode = paCode;
    }

    public void setWorkorderType(String workorderType)
    {
        this.workorderType = workorderType;
    }

    public String getWorkorderType()
    {
        return workorderType;
    }

    public String getPaCode() 
    {
        return paCode;
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
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }
    public void setBlankThickness(BigDecimal blankThickness) 
    {
        this.blankThickness = blankThickness;
    }

    public BigDecimal getBlankThickness() 
    {
        return blankThickness;
    }
    public void setBlankWidth(BigDecimal blankWidth) 
    {
        this.blankWidth = blankWidth;
    }

    public BigDecimal getBlankWidth() 
    {
        return blankWidth;
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
    public void setProductStatus(String productStatus) 
    {
        this.productStatus = productStatus;
    }

    public String getProductStatus() 
    {
        return productStatus;
    }
    public void setMaterialCode(String materialCode) 
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode() 
    {
        return materialCode;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setToleranceType(String toleranceType) 
    {
        this.toleranceType = toleranceType;
    }

    public String getToleranceType() 
    {
        return toleranceType;
    }
    public void setToleranceCount(BigDecimal toleranceCount) 
    {
        this.toleranceCount = toleranceCount;
    }

    public BigDecimal getToleranceCount() 
    {
        return toleranceCount;
    }
    public void setTensileStrength(String tensileStrength) 
    {
        this.tensileStrength = tensileStrength;
    }

    public String getTensileStrength() 
    {
        return tensileStrength;
    }
    public void setTensileStrengthStart(BigDecimal tensileStrengthStart) 
    {
        this.tensileStrengthStart = tensileStrengthStart;
    }

    public BigDecimal getTensileStrengthStart() 
    {
        return tensileStrengthStart;
    }
    public void setTensileStrengthEnd(BigDecimal tensileStrengthEnd) 
    {
        this.tensileStrengthEnd = tensileStrengthEnd;
    }

    public BigDecimal getTensileStrengthEnd() 
    {
        return tensileStrengthEnd;
    }
    public void setHardness(String hardness) 
    {
        this.hardness = hardness;
    }

    public String getHardness() 
    {
        return hardness;
    }
    public void setHardnessStart(BigDecimal hardnessStart) 
    {
        this.hardnessStart = hardnessStart;
    }

    public BigDecimal getHardnessStart() 
    {
        return hardnessStart;
    }
    public void setHardnessEnd(BigDecimal hardnessEnd) 
    {
        this.hardnessEnd = hardnessEnd;
    }

    public BigDecimal getHardnessEnd() 
    {
        return hardnessEnd;
    }
    public void setNeedCount(Long needCount) 
    {
        this.needCount = needCount;
    }

    public Long getNeedCount() 
    {
        return needCount;
    }
    public void setAllocatedCount(Long allocatedCount) 
    {
        this.allocatedCount = allocatedCount;
    }

    public Long getAllocatedCount() 
    {
        return allocatedCount;
    }
    public void setCompletedCount(Long completedCount) 
    {
        this.completedCount = completedCount;
    }

    public Long getCompletedCount() 
    {
        return completedCount;
    }
    public void setProgressCount(Long progressCount) 
    {
        this.progressCount = progressCount;
    }

    public Long getProgressCount() 
    {
        return progressCount;
    }
    public void setSorrCount(Long sorrCount) 
    {
        this.sorrCount = sorrCount;
    }

    public Long getSorrCount() 
    {
        return sorrCount;
    }
    public void setPaStatus(String paStatus) 
    {
        this.paStatus = paStatus;
    }

    public String getPaStatus() 
    {
        return paStatus;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paId", getPaId())
            .append("paCode", getPaCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("weight", getWeight())
            .append("blankThickness", getBlankThickness())
            .append("blankWidth", getBlankWidth())
            .append("productThickness", getProductThickness())
            .append("productWidth", getProductWidth())
            .append("productStatus", getProductStatus())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("toleranceType", getToleranceType())
            .append("toleranceCount", getToleranceCount())
            .append("tensileStrength", getTensileStrength())
            .append("tensileStrengthStart", getTensileStrengthStart())
            .append("tensileStrengthEnd", getTensileStrengthEnd())
            .append("hardness", getHardness())
            .append("hardnessStart", getHardnessStart())
            .append("hardnessEnd", getHardnessEnd())
            .append("needCount", getNeedCount())
            .append("allocatedCount", getAllocatedCount())
            .append("completedCount", getCompletedCount())
            .append("progressCount", getProgressCount())
            .append("sorrCount", getSorrCount())
            .append("paStatus", getPaStatus())
            .append("workorderType", getWorkorderType())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
