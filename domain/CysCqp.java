package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 粗切边对象 cys_cqp
 * 
 * @author yinjinlu
 * @date 2025-05-05
 */
public class CysCqp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 粗切边工序id */
    private String roughcutId;

    /** 粗切坯工序编码 */
    @Excel(name = "粗切坯工序编码")
    private String roughcutCode;

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

    /** 切前规格 */
    @Excel(name = "切前规格")
    private BigDecimal cutBeforeMilling;

    /** 切后规格 */
    @Excel(name = "切后规格")
    private BigDecimal cutAfterMilling;

    /** 切前重量 */
    @Excel(name = "切前重量")
    private BigDecimal cutBeforeWeight;

    /** 切后重量 */
    @Excel(name = "切后重量")
    private BigDecimal cutAfterWeight;

    /** 切边时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "切边时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date cutTime;

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

    public void setRoughcutId(String roughcutId) 
    {
        this.roughcutId = roughcutId;
    }

    public String getRoughcutId() 
    {
        return roughcutId;
    }
    public void setRoughcutCode(String roughcutCode) 
    {
        this.roughcutCode = roughcutCode;
    }

    public String getRoughcutCode() 
    {
        return roughcutCode;
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
    public void setCutBeforeMilling(BigDecimal cutBeforeMilling) 
    {
        this.cutBeforeMilling = cutBeforeMilling;
    }

    public BigDecimal getCutBeforeMilling() 
    {
        return cutBeforeMilling;
    }
    public void setCutAfterMilling(BigDecimal cutAfterMilling) 
    {
        this.cutAfterMilling = cutAfterMilling;
    }

    public BigDecimal getCutAfterMilling() 
    {
        return cutAfterMilling;
    }
    public void setCutBeforeWeight(BigDecimal cutBeforeWeight) 
    {
        this.cutBeforeWeight = cutBeforeWeight;
    }

    public BigDecimal getCutBeforeWeight() 
    {
        return cutBeforeWeight;
    }
    public void setCutAfterWeight(BigDecimal cutAfterWeight) 
    {
        this.cutAfterWeight = cutAfterWeight;
    }

    public BigDecimal getCutAfterWeight() 
    {
        return cutAfterWeight;
    }
    public void setCutTime(Date cutTime) 
    {
        this.cutTime = cutTime;
    }

    public Date getCutTime() 
    {
        return cutTime;
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
            .append("roughcutId", getRoughcutId())
            .append("roughcutCode", getRoughcutCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("deviceName", getDeviceName())
            .append("cutBeforeMilling", getCutBeforeMilling())
            .append("cutAfterMilling", getCutAfterMilling())
            .append("cutBeforeWeight", getCutBeforeWeight())
            .append("cutAfterWeight", getCutAfterWeight())
            .append("cutTime", getCutTime())
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
