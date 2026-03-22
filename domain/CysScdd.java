package com.huaan.mes.md.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 生产订单对象 cys_scdd
 * 
 * @author yinjinlu
 * @date 2025-01-20
 */
public class CysScdd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 生产订单id */
    private Long porderId;

    /** 生产订单编码 */
    @Excel(name = "生产订单编码")
    private String porderCode;

    /** 生产订单名称 */
    @Excel(name = "生产订单名称")
    private String porderName;

    /** 牌号 */
    @Excel(name = "牌号")
    private String brand;

    /** 订单来源 */
    @Excel(name = "订单来源")
    private String orderSource;

    /** 计划订单编号 */
    @Excel(name = "计划订单编号")
    private String plannedOrdersCode;

    /** 工单数量 */
    @Excel(name = "工单数量")
    private Long workCount;

    /** 需求时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date demandTime;

    /** 产品id */
    @Excel(name = "产品id")
    private String itemId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String itemCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String itemName;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 客户id */
    @Excel(name = "客户id")
    private Long clientId;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String clientCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String clientName;

    /** 性能要求 */
    @Excel(name = "性能要求")
    private String performanceReq;

    /** 质量要求 */
    @Excel(name = "质量要求")
    private String qualityReq;

    /** 包装要求 */
    @Excel(name = "包装要求")
    private String packageReq;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setPorderId(Long porderId) 
    {
        this.porderId = porderId;
    }

    public Long getPorderId() 
    {
        return porderId;
    }
    public void setPorderCode(String porderCode) 
    {
        this.porderCode = porderCode;
    }

    public String getPorderCode() 
    {
        return porderCode;
    }
    public void setPorderName(String porderName) 
    {
        this.porderName = porderName;
    }

    public String getPorderName() 
    {
        return porderName;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setOrderSource(String orderSource) 
    {
        this.orderSource = orderSource;
    }

    public String getOrderSource() 
    {
        return orderSource;
    }
    public void setPlannedOrdersCode(String plannedOrdersCode) 
    {
        this.plannedOrdersCode = plannedOrdersCode;
    }

    public String getPlannedOrdersCode() 
    {
        return plannedOrdersCode;
    }
    public void setWorkCount(Long workCount) 
    {
        this.workCount = workCount;
    }

    public Long getWorkCount() 
    {
        return workCount;
    }
    public void setDemandTime(Date demandTime) 
    {
        this.demandTime = demandTime;
    }

    public Date getDemandTime() 
    {
        return demandTime;
    }
    public void setItemId(String itemId) 
    {
        this.itemId = itemId;
    }

    public String getItemId() 
    {
        return itemId;
    }
    public void setItemCode(String itemCode) 
    {
        this.itemCode = itemCode;
    }

    public String getItemCode() 
    {
        return itemCode;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setClientId(Long clientId) 
    {
        this.clientId = clientId;
    }

    public Long getClientId() 
    {
        return clientId;
    }
    public void setClientCode(String clientCode) 
    {
        this.clientCode = clientCode;
    }

    public String getClientCode() 
    {
        return clientCode;
    }
    public void setClientName(String clientName) 
    {
        this.clientName = clientName;
    }

    public String getClientName() 
    {
        return clientName;
    }
    public void setPerformanceReq(String performanceReq) 
    {
        this.performanceReq = performanceReq;
    }

    public String getPerformanceReq() 
    {
        return performanceReq;
    }
    public void setQualityReq(String qualityReq) 
    {
        this.qualityReq = qualityReq;
    }

    public String getQualityReq() 
    {
        return qualityReq;
    }
    public void setPackageReq(String packageReq) 
    {
        this.packageReq = packageReq;
    }

    public String getPackageReq() 
    {
        return packageReq;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("porderId", getPorderId())
            .append("porderCode", getPorderCode())
            .append("porderName", getPorderName())
            .append("brand", getBrand())
            .append("orderSource", getOrderSource())
            .append("plannedOrdersCode", getPlannedOrdersCode())
            .append("workCount", getWorkCount())
            .append("demandTime", getDemandTime())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("unit", getUnit())
            .append("clientId", getClientId())
            .append("clientCode", getClientCode())
            .append("clientName", getClientName())
            .append("performanceReq", getPerformanceReq())
            .append("qualityReq", getQualityReq())
            .append("packageReq", getPackageReq())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
