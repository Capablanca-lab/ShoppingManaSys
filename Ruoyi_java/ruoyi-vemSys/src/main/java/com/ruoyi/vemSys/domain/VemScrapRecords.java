package com.ruoyi.vemSys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售货机报废对象 vem_scrap_records
 * 
 * @author ruoyi
 * @date 2025-02-19
 */
public class VemScrapRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 单据id */
    private Long scrapId;

    /** 售货机id */
    @Excel(name = "售货机id")
    private Long vemId;

    /** 售货机公司品牌 */
    @Excel(name = "售货机公司品牌")
    private String vemVersion;

    /** 废弃原因 */
    @Excel(name = "废弃原因")
    private String scrapReason;

    /** 扩展字段 */
    private String extendField;

    public void setScrapId(Long scrapId) 
    {
        this.scrapId = scrapId;
    }

    public Long getScrapId() 
    {
        return scrapId;
    }
    public void setVemId(Long vemId) 
    {
        this.vemId = vemId;
    }

    public Long getVemId() 
    {
        return vemId;
    }
    public void setVemVersion(String vemVersion) 
    {
        this.vemVersion = vemVersion;
    }

    public String getVemVersion() 
    {
        return vemVersion;
    }
    public void setScrapReason(String scrapReason) 
    {
        this.scrapReason = scrapReason;
    }

    public String getScrapReason() 
    {
        return scrapReason;
    }
    public void setExtendField(String extendField) 
    {
        this.extendField = extendField;
    }

    public String getExtendField() 
    {
        return extendField;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scrapId", getScrapId())
            .append("vemId", getVemId())
            .append("vemVersion", getVemVersion())
            .append("scrapReason", getScrapReason())
            .append("extendField", getExtendField())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
