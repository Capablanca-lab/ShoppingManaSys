package com.ruoyi.vemSys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 售货机维修记录对象 vem_repair_records
 * 
 * @author ruoyi
 * @date 2025-03-12
 */
public class VemRepairRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维修记录id */
    @Excel(name = "维修记录id")
    private Long recordId;

    /** 售货机id */
    @Excel(name = "售货机id")
    private Long vemId;

    /** 售货机位置 */
    @Excel(name = "售货机位置")
    private String vemLocation;

    /** 维修者 */
    @Excel(name = "维修者")
    private String Repairers;

    /** 维修原因 */
    @Excel(name = "维修原因")
    private String repairIssues;

    /** 是否维修完成 */
    @Excel(name = "是否维修完成")
    private String repairCompelete;

    /** 扩展字段 */
    @Excel(name = "扩展字段")
    private String extendField;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;


    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setVemId(Long vemId) 
    {
        this.vemId = vemId;
    }

    public Long getVemId() 
    {
        return vemId;
    }
    public void setVemLocation(String vemLocation) 
    {
        this.vemLocation = vemLocation;
    }

    public String getVemLocation() 
    {
        return vemLocation;
    }
    public void setRepairers(String Repairers) 
    {
        this.Repairers = Repairers;
    }

    public String getRepairers() 
    {
        return Repairers;
    }
    public void setRepairIssues(String repairIssues) 
    {
        this.repairIssues = repairIssues;
    }

    public String getRepairIssues() 
    {
        return repairIssues;
    }
    public void setRepairCompelete(String repairCompelete) 
    {
        this.repairCompelete = repairCompelete;
    }

    public String getRepairCompelete() 
    {
        return repairCompelete;
    }
    public void setExtendField(String extendField) 
    {
        this.extendField = extendField;
    }

    public String getExtendField() 
    {
        return extendField;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public void getCreatetime(Date createTime)
    {
        this.createtime = createtime;
    }

    public Date getUpdatetime()
    {
        return updatetime;
    }

    public void getUpdatetime(Date updateTime)
    {
        this.updatetime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("vemId", getVemId())
            .append("vemLocation", getVemLocation())
            .append("Repairers", getRepairers())
            .append("repairIssues", getRepairIssues())
            .append("repairCompelete", getRepairCompelete())
            .append("extendField", getExtendField())
            .append("createtime", getCreatetime())
            .append("updatetime", getUpdatetime())
            .toString();
    }
}
