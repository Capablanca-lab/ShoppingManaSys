package com.ruoyi.product.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品信息对象 product_info
 * 
 * @author ruoyi
 * @date 2025-02-15
 */
public class ProductInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long productNumber;

    /** 商品进价 */
    @Excel(name = "商品进价")
    private Long productCost;

    /** 商品售价 */
    @Excel(name = "商品售价")
    private Long productSell;

    /** 商品利润 */
    @Excel(name = "商品利润")
    private Long productProfile;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "建立时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createtime;

    /** 最后维护时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后维护时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatetime;

    /** 扩展字段 */
    private String extendfield;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setProductNumber(Long productNumber) 
    {
        this.productNumber = productNumber;
    }

    public Long getProductNumber() 
    {
        return productNumber;
    }
    public void setProductCost(Long productCost) 
    {
        this.productCost = productCost;
    }

    public Long getProductCost() 
    {
        return productCost;
    }
    public void setProductSell(Long productSell) 
    {
        this.productSell = productSell;
    }

    public Long getProductSell() 
    {
        return productSell;
    }
    public void setProductProfile(Long productProfile) 
    {
        this.productProfile = productProfile;
    }

    public Long getProductProfile() 
    {
        return productProfile;
    }
    public void setExtendfield(String extendfield) 
    {
        this.extendfield = extendfield;
    }

    public String getExtendfield() 
    {
        return extendfield;
    }

    public void setCreatetime(Date createdTime)
    {
        this.createtime = createdTime;
    }

    public Date getCreatetime()
    {
        return createtime;
    }
    public void setUpdatetime(Date lastMaintenanceDate)
    {
        this.updatetime = lastMaintenanceDate;
    }

    public Date getUpdatetime()
    {
        return updatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productName", getProductName())
            .append("productNumber", getProductNumber())
            .append("productCost", getProductCost())
            .append("productSell", getProductSell())
            .append("productProfile", getProductProfile())
            .append("createtime", getCreatetime())
            .append("updatetime", getUpdatetime())
            .append("extendfield", getExtendfield())
            .toString();
    }
}
