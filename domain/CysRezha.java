package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 热轧对象 cys_rezha
 * 
 * @author yinjinlu
 * @date 2025-04-29
 */
public class CysRezha extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 热轧工序id */
    private String hotrollId;

    /** 热轧工序编码 */
    @Excel(name = "热轧工序编码")
    private String hotrollCode;

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

    /** 加热炉位置 */
    @Excel(name = "加热炉位置")
    private String heatfLocation;

    /** 升温时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "升温时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date heatTime;

    /** 开轧时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开轧时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date rollTime;

    /** 加热温度 */
    @Excel(name = "加热温度")
    private BigDecimal heatTemperature;

    /** 装炉日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "装炉日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date installationDate;

    /** 轧前重量 */
    @Excel(name = "轧前重量")
    private BigDecimal weightBeforeRoll;

    /** 轧后重量 */
    @Excel(name = "轧后重量")
    private BigDecimal weightAfterRoll;

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

    public void setHotrollId(String hotrollId) 
    {
        this.hotrollId = hotrollId;
    }

    public String getHotrollId() 
    {
        return hotrollId;
    }
    public void setHotrollCode(String hotrollCode) 
    {
        this.hotrollCode = hotrollCode;
    }

    public String getHotrollCode() 
    {
        return hotrollCode;
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
    public void setHeatfLocation(String heatfLocation) 
    {
        this.heatfLocation = heatfLocation;
    }

    public String getHeatfLocation() 
    {
        return heatfLocation;
    }
    public void setHeatTime(Date heatTime) 
    {
        this.heatTime = heatTime;
    }

    public Date getHeatTime() 
    {
        return heatTime;
    }
    public void setRollTime(Date rollTime) 
    {
        this.rollTime = rollTime;
    }

    public Date getRollTime() 
    {
        return rollTime;
    }
    public void setHeatTemperature(BigDecimal heatTemperature) 
    {
        this.heatTemperature = heatTemperature;
    }

    public BigDecimal getHeatTemperature() 
    {
        return heatTemperature;
    }
    public void setInstallationDate(Date installationDate) 
    {
        this.installationDate = installationDate;
    }

    public Date getInstallationDate() 
    {
        return installationDate;
    }
    public void setWeightBeforeRoll(BigDecimal weightBeforeRoll) 
    {
        this.weightBeforeRoll = weightBeforeRoll;
    }

    public BigDecimal getWeightBeforeRoll() 
    {
        return weightBeforeRoll;
    }
    public void setWeightAfterRoll(BigDecimal weightAfterRoll) 
    {
        this.weightAfterRoll = weightAfterRoll;
    }

    public BigDecimal getWeightAfterRoll() 
    {
        return weightAfterRoll;
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
            .append("hotrollId", getHotrollId())
            .append("hotrollCode", getHotrollCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("heatfLocation", getHeatfLocation())
            .append("heatTime", getHeatTime())
            .append("rollTime", getRollTime())
            .append("heatTemperature", getHeatTemperature())
            .append("installationDate", getInstallationDate())
            .append("weightBeforeRoll", getWeightBeforeRoll())
            .append("weightAfterRoll", getWeightAfterRoll())
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
