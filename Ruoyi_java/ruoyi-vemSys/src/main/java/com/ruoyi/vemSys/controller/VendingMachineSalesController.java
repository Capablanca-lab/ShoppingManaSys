package com.ruoyi.vemSys.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.vemSys.domain.VendingMachineSales;
import com.ruoyi.vemSys.service.IVendingMachineSalesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售货机单商品销售信息Controller
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
@RestController
@RequestMapping("/vemSys/sales")
public class VendingMachineSalesController extends BaseController
{
    @Autowired
    private IVendingMachineSalesService vendingMachineSalesService;

    /**
     * 查询售货机单商品销售信息列表
     */
    @PreAuthorize("@ss.hasPermi('vemSys:sales:list')")
    @GetMapping("/list")
    public TableDataInfo list(VendingMachineSales vendingMachineSales)
    {
        startPage();
        List<VendingMachineSales> list = vendingMachineSalesService.selectVendingMachineSalesList(vendingMachineSales);
        return getDataTable(list);
    }

    /**
     * 导出售货机单商品销售信息列表
     */
    @PreAuthorize("@ss.hasPermi('vemSys:sales:export')")
    @Log(title = "售货机单商品销售信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VendingMachineSales vendingMachineSales)
    {
        List<VendingMachineSales> list = vendingMachineSalesService.selectVendingMachineSalesList(vendingMachineSales);
        ExcelUtil<VendingMachineSales> util = new ExcelUtil<VendingMachineSales>(VendingMachineSales.class);
        util.exportExcel(response, list, "售货机单商品销售信息数据");
    }

    /**
     * 获取售货机单商品销售信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('vemSys:sales:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vendingMachineSalesService.selectVendingMachineSalesById(id));
    }

    /**
     * 新增售货机单商品销售信息
     */
    @PreAuthorize("@ss.hasPermi('vemSys:sales:add')")
    @Log(title = "售货机单商品销售信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VendingMachineSales vendingMachineSales)
    {
        return toAjax(vendingMachineSalesService.insertVendingMachineSales(vendingMachineSales));
    }

    /**
     * 修改售货机单商品销售信息
     */
    @PreAuthorize("@ss.hasPermi('vemSys:sales:edit')")
    @Log(title = "售货机单商品销售信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VendingMachineSales vendingMachineSales)
    {
        return toAjax(vendingMachineSalesService.updateVendingMachineSales(vendingMachineSales));
    }

    /**
     * 删除售货机单商品销售信息
     */
    @PreAuthorize("@ss.hasPermi('vemSys:sales:remove')")
    @Log(title = "售货机单商品销售信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vendingMachineSalesService.deleteVendingMachineSalesByIds(ids));
    }
}
