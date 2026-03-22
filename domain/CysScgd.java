package com.huaan.mes.md.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 生产工单对象 cys_scgd
 * 
 * @author yinjinlu
 * @date 2025-06-27
 */
public class CysScgd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 生产工单id */
    private Long workId;

    /** 生产工单编码 */
    @Excel(name = "生产工单编码")
    private String workCode;

    /** 生产订单编码 */
    @Excel(name = "生产订单编码")
    private String porderCode;

    /** 生产订单名称 */
    @Excel(name = "生产订单名称")
    private String porderName;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;

    /** 工单状态 */
    @Excel(name = "工单状态")
    private String workStatus;

    /** 当前工序 */
    @Excel(name = "当前工序")
    private String thisProcess;

    // 标注为非数据库字段
    private List<String> thisProcessList; // 用于多工序查询

    /** 分配状态 */
    @Excel(name = "分配状态")
    private String allocationStatus;

    /** 开工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开工时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 完工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "完工时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 牌号 */
    @Excel(name = "牌号")
    private String brand;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNumber;

    /** 计划订单编号 */
    @Excel(name = "计划订单编号")
    private String plannedOrdersCode;

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

    /** 工单类型 */
    @Excel(name = "工单类型")
    private String workOrderType;

    public List<String> getThisProcessList() {
        return thisProcessList;
    }

    public void setWorkOrderType(String workOrderType)
    {
        this.workOrderType = workOrderType;
    }

    public String getWorkOrderType()
    {
        return workOrderType;
    }

    public void setThisProcessList(List<String> thisProcessList) {
        this.thisProcessList = thisProcessList;
    }

    public void setWorkId(Long workId) 
    {
        this.workId = workId;
    }

    public Long getWorkId() 
    {
        return workId;
    }
    public void setWorkCode(String workCode) 
    {
        this.workCode = workCode;
    }

    public String getWorkCode() 
    {
        return workCode;
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
    public void setSubmitTime(Date submitTime) 
    {
        this.submitTime = submitTime;
    }

    public Date getSubmitTime() 
    {
        return submitTime;
    }
    public void setWorkStatus(String workStatus) 
    {
        this.workStatus = workStatus;
    }

    public String getWorkStatus() 
    {
        return workStatus;
    }
    public void setThisProcess(String thisProcess) 
    {
        this.thisProcess = thisProcess;
    }

    public String getThisProcess() 
    {
        return thisProcess;
    }
    public void setAllocationStatus(String allocationStatus) 
    {
        this.allocationStatus = allocationStatus;
    }

    public String getAllocationStatus() 
    {
        return allocationStatus;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
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
    public void setPlannedOrdersCode(String plannedOrdersCode) 
    {
        this.plannedOrdersCode = plannedOrdersCode;
    }

    public String getPlannedOrdersCode() 
    {
        return plannedOrdersCode;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("workId", getWorkId())
            .append("workCode", getWorkCode())
            .append("porderCode", getPorderCode())
            .append("porderName", getPorderName())
            .append("submitTime", getSubmitTime())
            .append("workStatus", getWorkStatus())
            .append("thisProcess", getThisProcess())
            .append("allocationStatus", getAllocationStatus())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("plannedOrdersCode", getPlannedOrdersCode())
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
            .append("workOrderType", getWorkOrderType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
