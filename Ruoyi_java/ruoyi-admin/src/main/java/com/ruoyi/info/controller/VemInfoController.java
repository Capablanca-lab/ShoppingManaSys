package com.ruoyi.info.controller;

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
import com.ruoyi.info.domain.VemInfo;
import com.ruoyi.info.service.IVemInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售货机管理Controller
 * 
 * @author admin
 * @date 2024-11-20
 */
@RestController
@RequestMapping("/info/info")
public class VemInfoController extends BaseController
{
    @Autowired
    private IVemInfoService vemInfoService;

    /**
     * 查询售货机管理列表
     */
    @PreAuthorize("@ss.hasPermi('info:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(VemInfo vemInfo)
    {
        startPage();
        List<VemInfo> list = vemInfoService.selectVemInfoList(vemInfo);
        return getDataTable(list);
    }

    /**
     * 导出售货机管理列表
     */
    @PreAuthorize("@ss.hasPermi('info:info:export')")
    @Log(title = "售货机管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VemInfo vemInfo)
    {
        List<VemInfo> list = vemInfoService.selectVemInfoList(vemInfo);
        ExcelUtil<VemInfo> util = new ExcelUtil<VemInfo>(VemInfo.class);
        util.exportExcel(response, list, "售货机管理数据");
    }

    /**
     * 获取售货机管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vemInfoService.selectVemInfoById(id));
    }

    /**
     * 新增售货机管理
     */
    @PreAuthorize("@ss.hasPermi('info:info:add')")
    @Log(title = "售货机管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VemInfo vemInfo)
    {
        return toAjax(vemInfoService.insertVemInfo(vemInfo));
    }

    /**
     * 修改售货机管理
     */
    @PreAuthorize("@ss.hasPermi('info:info:edit')")
    @Log(title = "售货机管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VemInfo vemInfo)
    {
        return toAjax(vemInfoService.updateVemInfo(vemInfo));
    }

    /**
     * 删除售货机管理
     */
    @PreAuthorize("@ss.hasPermi('info:info:remove')")
    @Log(title = "售货机管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vemInfoService.deleteVemInfoByIds(ids));
    }
}
