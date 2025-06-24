package com.ruoyi.profile.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.product.domain.ProductInfo;
/**
 * 售货机利润对象 vem_profile
 * 
 * @author ruoyi
 * @date 2025-02-17
 */
public class VemProfile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 售货机id */
    @Excel(name = "售货机id")
    private Long vemId;

    /** 售出总件数 */
    @Excel(name = "售出总件数")
    private Long soldQuantity;

    /** 剩余总数量 */
    @Excel(name = "剩余总数量")
    private Long remainingQuantity;

    /** 总利润 */
    @Excel(name = "总利润")
    private Long totalProfit;

    /** 扩展字段 */
    private String extendInfo;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 商品信息1信息 */
    private List<ProductInfo> productInfoList;

    public void setVemId(Long vemId) 
    {
        this.vemId = vemId;
    }

    public Long getVemId() 
    {
        return vemId;
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
    public void setTotalProfit(Long totalProfit) 
    {
        this.totalProfit = totalProfit;
    }

    public Long getTotalProfit() 
    {
        return totalProfit;
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

    public List<ProductInfo> getProductInfoList()
    {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> productInfoList)
    {
        this.productInfoList = productInfoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vemId", getVemId())
            .append("soldQuantity", getSoldQuantity())
            .append("remainingQuantity", getRemainingQuantity())
            .append("totalProfit", getTotalProfit())
            .append("extendInfo", getExtendInfo())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("productInfoList", getProductInfoList())
            .toString();
    }
}
