package com.ruoyi.info.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售货机管理对象 vem_info
 * 
 * @author admin
 * @date 2024-11-20
 */
public class VemInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 售货机id */
    @Excel(name = "售货机id")
    private Long id;

    /** 售货机位置 */
    @Excel(name = "售货机位置")
    private String location;

    /** 售货机状态 */
    @Excel(name = "售货机状态")
    private String status;

    /** 售货机公司品牌 */
    @Excel(name = "售货机公司品牌")
    private String version;

    /** 上架货物供应商 */
    @Excel(name = "上架货物供应商")
    private String suppliers;

    /** 建立时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "建立时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 最后维护时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后维护时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastMaintenanceDate;

    /** 扩展内容 */
    private String extendfield;

    /** 额外信息 */
    private String comment;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setSuppliers(String suppliers) 
    {
        this.suppliers = suppliers;
    }

    public String getSuppliers() 
    {
        return suppliers;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setLastMaintenanceDate(Date lastMaintenanceDate) 
    {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public Date getLastMaintenanceDate() 
    {
        return lastMaintenanceDate;
    }
    public void setExtendfield(String extendfield) 
    {
        this.extendfield = extendfield;
    }

    public String getExtendfield() 
    {
        return extendfield;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("location", getLocation())
            .append("status", getStatus())
            .append("version", getVersion())
            .append("suppliers", getSuppliers())
            .append("createdTime", getCreatedTime())
            .append("lastMaintenanceDate", getLastMaintenanceDate())
            .append("extendfield", getExtendfield())
            .append("comment", getComment())
            .toString();
    }
}
