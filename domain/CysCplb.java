package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 成品列对象 cys_cplb
 * 
 * @author yinjinlu
 * @date 2025-04-02
 */
public class CysCplb extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成品列表id */
    private String cplbId;

    /** 成品列表编码 */
    @Excel(name = "成品列表编码")
    private String cplbCode;

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

    /** 产品重量 */
    @Excel(name = "产品重量")
    private BigDecimal weight;

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
    private BigDecimal electricalConductivity;

    /** 入库状态 */
    @Excel(name = "入库状态")
    private String warehouseStatus;

    /** 员工id */
    @Excel(name = "员工id")
    private Long userId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String userName;

    public void setCplbId(String cplbId) 
    {
        this.cplbId = cplbId;
    }

    public String getCplbId() 
    {
        return cplbId;
    }
    public void setCplbCode(String cplbCode) 
    {
        this.cplbCode = cplbCode;
    }

    public String getCplbCode() 
    {
        return cplbCode;
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
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
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
    public void setWarehouseStatus(String warehouseStatus) 
    {
        this.warehouseStatus = warehouseStatus;
    }

    public String getWarehouseStatus() 
    {
        return warehouseStatus;
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
            .append("cplbId", getCplbId())
            .append("cplbCode", getCplbCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("productStatus", getProductStatus())
            .append("productThickness", getProductThickness())
            .append("productWidth", getProductWidth())
            .append("weight", getWeight())
            .append("tensileStrength", getTensileStrength())
            .append("elongation", getElongation())
            .append("hardness", getHardness())
            .append("cupConvexValue", getCupConvexValue())
            .append("electricalConductivity", getElectricalConductivity())
            .append("warehouseStatus", getWarehouseStatus())
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
