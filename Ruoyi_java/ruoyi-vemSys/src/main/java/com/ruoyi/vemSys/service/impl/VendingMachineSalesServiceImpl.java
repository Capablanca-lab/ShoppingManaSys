package com.ruoyi.vemSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vemSys.mapper.VendingMachineSalesMapper;
import com.ruoyi.vemSys.domain.VendingMachineSales;
import com.ruoyi.vemSys.service.IVendingMachineSalesService;

/**
 * 售货机单商品销售信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
@Service
public class VendingMachineSalesServiceImpl implements IVendingMachineSalesService 
{
    @Autowired
    private VendingMachineSalesMapper vendingMachineSalesMapper;

    /**
     * 查询售货机单商品销售信息
     * 
     * @param id 售货机单商品销售信息主键
     * @return 售货机单商品销售信息
     */
    @Override
    public VendingMachineSales selectVendingMachineSalesById(Long id)
    {
        return vendingMachineSalesMapper.selectVendingMachineSalesById(id);
    }

    /**
     * 查询售货机单商品销售信息列表
     * 
     * @param vendingMachineSales 售货机单商品销售信息
     * @return 售货机单商品销售信息
     */
    @Override
    public List<VendingMachineSales> selectVendingMachineSalesList(VendingMachineSales vendingMachineSales)
    {
        return vendingMachineSalesMapper.selectVendingMachineSalesList(vendingMachineSales);
    }

    /**
     * 新增售货机单商品销售信息
     * 
     * @param vendingMachineSales 售货机单商品销售信息
     * @return 结果
     */
    @Override
    public int insertVendingMachineSales(VendingMachineSales vendingMachineSales)
    {
        return vendingMachineSalesMapper.insertVendingMachineSales(vendingMachineSales);
    }

    /**
     * 修改售货机单商品销售信息
     * 
     * @param vendingMachineSales 售货机单商品销售信息
     * @return 结果
     */
    @Override
    public int updateVendingMachineSales(VendingMachineSales vendingMachineSales)
    {
        return vendingMachineSalesMapper.updateVendingMachineSales(vendingMachineSales);
    }

    /**
     * 批量删除售货机单商品销售信息
     * 
     * @param ids 需要删除的售货机单商品销售信息主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineSalesByIds(Long[] ids)
    {
        return vendingMachineSalesMapper.deleteVendingMachineSalesByIds(ids);
    }

    /**
     * 删除售货机单商品销售信息信息
     * 
     * @param id 售货机单商品销售信息主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineSalesById(Long id)
    {
        return vendingMachineSalesMapper.deleteVendingMachineSalesById(id);
    }
}
