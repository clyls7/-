package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 分切对象 cys_fq
 * 
 * @author yinjinlu
 * @date 2025-04-02
 */
public class CysFq extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分切id */
    private String fqId;

    /** 分切编码 */
    @Excel(name = "分切编码")
    private String fqCode;

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

    /** 产品厚度 */
    @Excel(name = "产品厚度")
    private BigDecimal productThickness;

    /** 产品宽度 */
    @Excel(name = "产品宽度")
    private BigDecimal productWidth;

    /** 产品状态 */
    @Excel(name = "产品状态")
    private String productStatus;

    /** 分切状态 */
    @Excel(name = "分切状态")
    private String cuttingStatus;

    /** 分切段数 */
    @Excel(name = "分切段数")
    private Long cuttingSections;

    /** 员工id */
    @Excel(name = "员工id")
    private Long userId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String userName;

    public void setFqId(String fqId) 
    {
        this.fqId = fqId;
    }

    public String getFqId() 
    {
        return fqId;
    }
    public void setFqCode(String fqCode) 
    {
        this.fqCode = fqCode;
    }

    public String getFqCode() 
    {
        return fqCode;
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
    public void setCuttingStatus(String cuttingStatus) 
    {
        this.cuttingStatus = cuttingStatus;
    }

    public String getCuttingStatus() 
    {
        return cuttingStatus;
    }
    public void setCuttingSections(Long cuttingSections) 
    {
        this.cuttingSections = cuttingSections;
    }

    public Long getCuttingSections() 
    {
        return cuttingSections;
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
            .append("fqId", getFqId())
            .append("fqCode", getFqCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("weight", getWeight())
            .append("productThickness", getProductThickness())
            .append("productWidth", getProductWidth())
            .append("productStatus", getProductStatus())
            .append("cuttingStatus", getCuttingStatus())
            .append("cuttingSections", getCuttingSections())
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
