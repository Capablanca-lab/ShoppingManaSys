package com.ruoyi.vemSys.service;

import java.util.List;
import com.ruoyi.vemSys.domain.VendingMachineSales;

/**
 * 售货机单商品销售信息Service接口
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
public interface IVendingMachineSalesService 
{
    /**
     * 查询售货机单商品销售信息
     * 
     * @param id 售货机单商品销售信息主键
     * @return 售货机单商品销售信息
     */
    public VendingMachineSales selectVendingMachineSalesById(Long id);

    /**
     * 查询售货机单商品销售信息列表
     * 
     * @param vendingMachineSales 售货机单商品销售信息
     * @return 售货机单商品销售信息集合
     */
    public List<VendingMachineSales> selectVendingMachineSalesList(VendingMachineSales vendingMachineSales);

    /**
     * 新增售货机单商品销售信息
     * 
     * @param vendingMachineSales 售货机单商品销售信息
     * @return 结果
     */
    public int insertVendingMachineSales(VendingMachineSales vendingMachineSales);

    /**
     * 修改售货机单商品销售信息
     * 
     * @param vendingMachineSales 售货机单商品销售信息
     * @return 结果
     */
    public int updateVendingMachineSales(VendingMachineSales vendingMachineSales);

    /**
     * 批量删除售货机单商品销售信息
     * 
     * @param ids 需要删除的售货机单商品销售信息主键集合
     * @return 结果
     */
    public int deleteVendingMachineSalesByIds(Long[] ids);

    /**
     * 删除售货机单商品销售信息信息
     * 
     * @param id 售货机单商品销售信息主键
     * @return 结果
     */
    public int deleteVendingMachineSalesById(Long id);
}
