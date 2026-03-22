package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 改制对象 cys_gz
 * 
 * @author yinjinlu
 * @date 2025-05-12
 */
public class CysGz extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 改制id */
    private String gzId;

    /** 改制编码 */
    @Excel(name = "改制编码")
    private String gzCode;

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

    /** 改制产品厚度 */
    @Excel(name = "改制产品厚度")
    private BigDecimal restructuringThickness;

    /** 改制产品宽度 */
    @Excel(name = "改制产品宽度")
    private BigDecimal restructuringWidth;

    /** 改制产品状态 */
    @Excel(name = "改制产品状态")
    private String restructuringStatus;

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

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 员工id */
    @Excel(name = "员工id")
    private Long userId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String userName;

    public void setGzId(String gzId) 
    {
        this.gzId = gzId;
    }

    public String getGzId() 
    {
        return gzId;
    }
    public void setGzCode(String gzCode) 
    {
        this.gzCode = gzCode;
    }

    public String getGzCode() 
    {
        return gzCode;
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
    public void setRestructuringThickness(BigDecimal restructuringThickness) 
    {
        this.restructuringThickness = restructuringThickness;
    }

    public BigDecimal getRestructuringThickness() 
    {
        return restructuringThickness;
    }
    public void setRestructuringWidth(BigDecimal restructuringWidth) 
    {
        this.restructuringWidth = restructuringWidth;
    }

    public BigDecimal getRestructuringWidth() 
    {
        return restructuringWidth;
    }
    public void setRestructuringStatus(String restructuringStatus) 
    {
        this.restructuringStatus = restructuringStatus;
    }

    public String getRestructuringStatus() 
    {
        return restructuringStatus;
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
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("gzId", getGzId())
            .append("gzCode", getGzCode())
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
            .append("restructuringThickness", getRestructuringThickness())
            .append("restructuringWidth", getRestructuringWidth())
            .append("restructuringStatus", getRestructuringStatus())
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
            .append("status", getStatus())
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
