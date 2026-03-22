package com.huaan.mes.md.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.huaan.common.annotation.Excel;
import com.huaan.common.core.domain.BaseEntity;

/**
 * 熔铸对象 cys_rz
 * 
 * @author yinjinlu
 * @date 2025-06-17
 */
public class CysRz extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 熔铸工序id */
    private String casId;

    /** 熔铸工序编码 */
    @Excel(name = "熔铸工序编码")
    private String castingCode;

    /** 生产工单id */
    @Excel(name = "生产工单id")
    private Long workorderId;

    /** 生产工单编码 */
    @Excel(name = "生产工单编码")
    private String workorderCode;

    /** 牌号 */
    @Excel(name = "牌号")
    private String brand;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String usercode;

    /** 拉铸日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "拉铸日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date castingData;

    /** 炉号 */
    @Excel(name = "炉号")
    private String furnaceNo;

    /** 炉次 */
    @Excel(name = "炉次")
    private String furnaceHeat;

    /** 员工id */
    @Excel(name = "员工id")
    private Long userId;

    /** 员工名称 */
    @Excel(name = "员工名称")
    private String userName;

    /** 品位 */
    @Excel(name = "品位")
    private String grade;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNumber;

    /** 黄铜角料 */
    @Excel(name = "黄铜角料")
    private BigDecimal brassScrap;

    /** 紫铜 */
    @Excel(name = "紫铜")
    private BigDecimal copper;

    /** 锌块 */
    @Excel(name = "锌块")
    private BigDecimal zincBlock;

    /** 镀白料 */
    @Excel(name = "镀白料")
    private BigDecimal whitePlating;

    /** 电解铜 */
    @Excel(name = "电解铜")
    private BigDecimal electrolyticCopper;

    /** 重量 */
    @Excel(name = "重量")
    private BigDecimal weight;

    /** 铸锭规格 */
    @Excel(name = "铸锭规格")
    private String ingotSpecifications;

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

    /** 锯切前重量 */
    @Excel(name = "锯切前重量")
    private BigDecimal beforeSawing;

    /** 锯切后重量 */
    @Excel(name = "锯切后重量")
    private BigDecimal afterSawing;

    /** 质量判定 */
    @Excel(name = "质量判定")
    private String qualityJudgment;

    /** 异常原因 */
    @Excel(name = "异常原因")
    private String abnormalCause;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 来源 */
    @Excel(name = "来源")
    private String sourceRz;

    public void setCasId(String casId) 
    {
        this.casId = casId;
    }

    public String getCasId() 
    {
        return casId;
    }
    public void setCastingCode(String castingCode) 
    {
        this.castingCode = castingCode;
    }

    public String getCastingCode() 
    {
        return castingCode;
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
    public void setUsercode(String usercode) 
    {
        this.usercode = usercode;
    }

    public String getUsercode() 
    {
        return usercode;
    }
    public void setCastingData(Date castingData) 
    {
        this.castingData = castingData;
    }

    public Date getCastingData() 
    {
        return castingData;
    }
    public void setFurnaceNo(String furnaceNo) 
    {
        this.furnaceNo = furnaceNo;
    }

    public String getFurnaceNo() 
    {
        return furnaceNo;
    }
    public void setFurnaceHeat(String furnaceHeat) 
    {
        this.furnaceHeat = furnaceHeat;
    }

    public String getFurnaceHeat() 
    {
        return furnaceHeat;
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
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setBatchNumber(String batchNumber) 
    {
        this.batchNumber = batchNumber;
    }

    public String getBatchNumber() 
    {
        return batchNumber;
    }
    public void setBrassScrap(BigDecimal brassScrap) 
    {
        this.brassScrap = brassScrap;
    }

    public BigDecimal getBrassScrap() 
    {
        return brassScrap;
    }
    public void setCopper(BigDecimal copper) 
    {
        this.copper = copper;
    }

    public BigDecimal getCopper() 
    {
        return copper;
    }
    public void setZincBlock(BigDecimal zincBlock) 
    {
        this.zincBlock = zincBlock;
    }

    public BigDecimal getZincBlock() 
    {
        return zincBlock;
    }
    public void setWhitePlating(BigDecimal whitePlating) 
    {
        this.whitePlating = whitePlating;
    }

    public BigDecimal getWhitePlating() 
    {
        return whitePlating;
    }
    public void setElectrolyticCopper(BigDecimal electrolyticCopper) 
    {
        this.electrolyticCopper = electrolyticCopper;
    }

    public BigDecimal getElectrolyticCopper() 
    {
        return electrolyticCopper;
    }
    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }
    public void setIngotSpecifications(String ingotSpecifications) 
    {
        this.ingotSpecifications = ingotSpecifications;
    }

    public String getIngotSpecifications() 
    {
        return ingotSpecifications;
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
    public void setBeforeSawing(BigDecimal beforeSawing) 
    {
        this.beforeSawing = beforeSawing;
    }

    public BigDecimal getBeforeSawing() 
    {
        return beforeSawing;
    }
    public void setAfterSawing(BigDecimal afterSawing) 
    {
        this.afterSawing = afterSawing;
    }

    public BigDecimal getAfterSawing() 
    {
        return afterSawing;
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
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setSourceRz(String sourceRz)
    {
        this.sourceRz = sourceRz;
    }

    public String getSourceRz()
    {
        return sourceRz;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("casId", getCasId())
            .append("castingCode", getCastingCode())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("brand", getBrand())
            .append("usercode", getUsercode())
            .append("castingData", getCastingData())
            .append("furnaceNo", getFurnaceNo())
            .append("furnaceHeat", getFurnaceHeat())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("grade", getGrade())
            .append("batchNumber", getBatchNumber())
            .append("brassScrap", getBrassScrap())
            .append("copper", getCopper())
            .append("zincBlock", getZincBlock())
            .append("whitePlating", getWhitePlating())
            .append("electrolyticCopper", getElectrolyticCopper())
            .append("weight", getWeight())
            .append("ingotSpecifications", getIngotSpecifications())
            .append("testingTime", getTestingTime())
            .append("cu", getCu())
            .append("fe", getFe())
            .append("pb", getPb())
            .append("ai", getAi())
            .append("cd", getCd())
            .append("sn", getSn())
            .append("technicianId", getTechnicianId())
            .append("technicianName", getTechnicianName())
            .append("beforeSawing", getBeforeSawing())
            .append("afterSawing", getAfterSawing())
            .append("qualityJudgment", getQualityJudgment())
            .append("abnormalCause", getAbnormalCause())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("sourceRz", getSourceRz())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
