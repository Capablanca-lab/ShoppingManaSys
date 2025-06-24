package com.ruoyi.vemSys.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售货机单商品销售信息对象 vending_machine_sales
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
public class VendingMachineSales extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 销售记录ID（主键自增） */
    @Excel(name = "销售记录ID", readConverterExp = "主=键自增")
    private Long id;

    /** 售货机ID（外键，关联vem_info表） */
    @Excel(name = "售货机ID", readConverterExp = "外=键，关联vem_info表")
    private Long vemId;

    /** 商品ID（外键，关联Product_info表） */
    private Long productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 售出件数 */
    @Excel(name = "售出件数")
    private Long soldQuantity;

    /** 剩余数量 */
    @Excel(name = "剩余数量")
    private Long remainingQuantity;

    /** 总利润 */
    @Excel(name = "总利润")
    private BigDecimal totalProfit;

    /** 是否售罄（1=售罄，0=未售罄） */
    @Excel(name = "是否售罄", readConverterExp = "1==售罄，0=未售罄")
    private Integer isSoldOut;

    /** 扩展字段（JSON格式） */
    @Excel(name = "扩展字段", readConverterExp = "J=SON格式")
    private String extendInfo;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVemId(Long vemId) 
    {
        this.vemId = vemId;
    }

    public Long getVemId() 
    {
        return vemId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setSoldQuantity(Long soldQuantity) 
    {
        this.soldQuantity = soldQuantity;
    }

    public Long getSoldQuantity() 
    {
        return soldQuantity;
    }
    public void setRemainingQuantity(Long remainingQuantity) 
    {
        this.remainingQuantity = remainingQuantity;
    }

    public Long getRemainingQuantity() 
    {
        return remainingQuantity;
    }
    public void setTotalProfit(BigDecimal totalProfit) 
    {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getTotalProfit() 
    {
        return totalProfit;
    }
    public void setIsSoldOut(Integer isSoldOut) 
    {
        this.isSoldOut = isSoldOut;
    }

    public Integer getIsSoldOut() 
    {
        return isSoldOut;
    }
    public void setExtendInfo(String extendInfo) 
    {
        this.extendInfo = extendInfo;
    }

    public String getExtendInfo() 
    {
        return extendInfo;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vemId", getVemId())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("soldQuantity", getSoldQuantity())
            .append("remainingQuantity", getRemainingQuantity())
            .append("totalProfit", getTotalProfit())
            .append("isSoldOut", getIsSoldOut())
            .append("extendInfo", getExtendInfo())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
