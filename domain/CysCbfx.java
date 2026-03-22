package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 成本分析对象 cys_cbfx
 * 
 * @author yinjinlu
 * @date 2025-04-27
 */
public class CysCbfx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成本分析id */
    private Long costaId;

    /** 成本分析编码 */
    @Excel(name = "成本分析编码")
    private String costaCode;

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

    /** 品位 */
    @Excel(name = "品位")
    private String grade;

    /** 化验时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "化验时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date testingTime;

    /** 铜 */
    @Excel(name = "铜")
    private BigDecimal cu;

    /** 铁 */
    @Excel(name = "铁")
    private BigDecimal fe;

    /** 铅 */
    @Excel(name = "铅")
    private BigDecimal pb;

    /** 铝 */
    @Excel(name = "铝")
    private BigDecimal ai;

    /** 镉 */
    @Excel(name = "镉")
    private BigDecimal cd;

    /** 锡 */
    @Excel(name = "锡")
    private BigDecimal sn;

    /** 化验员id */
    @Excel(name = "化验员id")
    private Long technicianId;

    /** 化验员名称 */
    @Excel(name = "化验员名称")
    private String technicianName;

    /** 质量判定 */
    @Excel(name = "质量判定")
    private String qualityJudgment;

    /** 异常原因 */
    @Excel(name = "异常原因")
    private String abnormalCause;

    public void setCostaId(Long costaId) 
    {
        this.costaId = costaId;
    }

    public Long getCostaId() 
    {
        return costaId;
    }
    public void setCostaCode(String costaCode) 
    {
        this.costaCode = costaCode;
    }

    public String getCostaCode() 
    {
        return costaCode;
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
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setTestingTime(Date testingTime) 
    {
        this.testingTime = testingTime;
    }

    public Date getTestingTime() 
    {
        return testingTime;
    }
    public void setCu(BigDecimal cu) 
    {
        this.cu = cu;
    }

    public BigDecimal getCu() 
    {
        return cu;
    }
    public void setFe(BigDecimal fe) 
    {
        this.fe = fe;
    }

    public BigDecimal getFe() 
    {
        return fe;
    }
    public void setPb(BigDecimal pb) 
    {
        this.pb = pb;
    }

    public BigDecimal getPb() 
    {
        return pb;
    }
    public void setAi(BigDecimal ai) 
    {
        this.ai = ai;
    }

    public BigDecimal getAi() 
    {
        return ai;
    }
    public void setCd(BigDecimal cd) 
    {
        this.cd = cd;
    }

    public BigDecimal getCd() 
    {
        return cd;
    }
    public void setSn(BigDecimal sn) 
    {
        this.sn = sn;
    }

    public BigDecimal getSn() 
    {
        return sn;
    }
    public void setTechnicianId(Long technicianId) 
    {
        this.technicianId = technicianId;
    }

    public Long getTechnicianId() 
    {
        return technicianId;
    }
    public void setTechnicianName(String technicianName) 
    {
        this.technicianName = technicianName;
    }

    public String getTechnicianName() 
    {
        return technicianName;
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
            .append("costaId", getCostaId())
            .append("costaCode", getCostaCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("batchNumber", getBatchNumber())
            .append("grade", getGrade())
            .append("testingTime", getTestingTime())
            .append("cu", getCu())
            .append("fe", getFe())
            .append("pb", getPb())
            .append("ai", getAi())
            .append("cd", getCd())
            .append("sn", getSn())
            .append("technicianId", getTechnicianId())
            .append("technicianName", getTechnicianName())
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
