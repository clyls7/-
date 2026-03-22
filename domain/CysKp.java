package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 开坯对象 cys_kp
 * 
 * @author yinjinlu
 * @date 2025-05-04
 */
public class CysKp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 开坯工序id */
    private String doublesidedId;

    /** 开坯工序编码 */
    @Excel(name = "开坯工序编码")
    private String doublesidedCode;

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

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 轧前规格 */
    @Excel(name = "轧前规格")
    private BigDecimal spBeforeMilling;

    /** 轧后规格 */
    @Excel(name = "轧后规格")
    private BigDecimal spAfterMilling;

    /** 开坯前重量 */
    @Excel(name = "开坯前重量")
    private BigDecimal openBeforeWeight;

    /** 开坯后重量 */
    @Excel(name = "开坯后重量")
    private BigDecimal openAfterWeight;

    /** 粗轧时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "粗轧时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date rollingTime;

    /** 是否切边 */
    @Excel(name = "是否切边")
    private String whetherCut;

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

    public void setDoublesidedId(String doublesidedId) 
    {
        this.doublesidedId = doublesidedId;
    }

    public String getDoublesidedId() 
    {
        return doublesidedId;
    }
    public void setDoublesidedCode(String doublesidedCode) 
    {
        this.doublesidedCode = doublesidedCode;
    }

    public String getDoublesidedCode() 
    {
        return doublesidedCode;
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
    public void setDeviceName(String deviceName) 
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName() 
    {
        return deviceName;
    }
    public void setSpBeforeMilling(BigDecimal spBeforeMilling) 
    {
        this.spBeforeMilling = spBeforeMilling;
    }

    public BigDecimal getSpBeforeMilling() 
    {
        return spBeforeMilling;
    }
    public void setSpAfterMilling(BigDecimal spAfterMilling) 
    {
        this.spAfterMilling = spAfterMilling;
    }

    public BigDecimal getSpAfterMilling() 
    {
        return spAfterMilling;
    }
    public void setOpenBeforeWeight(BigDecimal openBeforeWeight) 
    {
        this.openBeforeWeight = openBeforeWeight;
    }

    public BigDecimal getOpenBeforeWeight() 
    {
        return openBeforeWeight;
    }
    public void setOpenAfterWeight(BigDecimal openAfterWeight) 
    {
        this.openAfterWeight = openAfterWeight;
    }

    public BigDecimal getOpenAfterWeight() 
    {
        return openAfterWeight;
    }
    public void setRollingTime(Date rollingTime) 
    {
        this.rollingTime = rollingTime;
    }

    public Date getRollingTime() 
    {
        return rollingTime;
    }
    public void setWhetherCut(String whetherCut) 
    {
        this.whetherCut = whetherCut;
    }

    public String getWhetherCut() 
    {
        return whetherCut;
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
            .append("doublesidedId", getDoublesidedId())
            .append("doublesidedCode", getDoublesidedCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("deviceName", getDeviceName())
            .append("spBeforeMilling", getSpBeforeMilling())
            .append("spAfterMilling", getSpAfterMilling())
            .append("openBeforeWeight", getOpenBeforeWeight())
            .append("openAfterWeight", getOpenAfterWeight())
            .append("rollingTime", getRollingTime())
            .append("whetherCut", getWhetherCut())
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
