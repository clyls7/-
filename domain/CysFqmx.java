package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 分切明细对象 cys_fqmx
 * 
 * @author yinjinlu
 * @date 2025-04-02
 */
public class CysFqmx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分切id */
    private String fqmxId;

    /** 分切编码 */
    @Excel(name = "分切编码")
    private String fqmxCode;

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

    /** 分切父id */
    @Excel(name = "分切父id")
    private Long cuttingParentid;

    /** 宽度 */
    @Excel(name = "宽度")
    private BigDecimal width;

    /** 重量 */
    @Excel(name = "重量")
    private BigDecimal weight;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String materialCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String materialName;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String userName;

    public void setFqmxId(String fqmxId) 
    {
        this.fqmxId = fqmxId;
    }

    public String getFqmxId() 
    {
        return fqmxId;
    }
    public void setFqmxCode(String fqmxCode) 
    {
        this.fqmxCode = fqmxCode;
    }

    public String getFqmxCode() 
    {
        return fqmxCode;
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
    public void setCuttingParentid(Long cuttingParentid) 
    {
        this.cuttingParentid = cuttingParentid;
    }

    public Long getCuttingParentid() 
    {
        return cuttingParentid;
    }
    public void setWidth(BigDecimal width) 
    {
        this.width = width;
    }

    public BigDecimal getWidth() 
    {
        return width;
    }
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
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
            .append("fqmxId", getFqmxId())
            .append("fqmxCode", getFqmxCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("cuttingParentid", getCuttingParentid())
            .append("width", getWidth())
            .append("weight", getWeight())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("userName", getUserName())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
