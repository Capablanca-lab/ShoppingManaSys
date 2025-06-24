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
import com.ruoyi.vemSys.domain.VemScrapRecords;
import com.ruoyi.vemSys.service.IVemScrapRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售货机报废Controller
 * 
 * @author ruoyi
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/scrap_records/records")
public class VemScrapRecordsController extends BaseController
{
    @Autowired
    private IVemScrapRecordsService vemScrapRecordsService;

    /**
     * 查询售货机报废列表
     */
    @PreAuthorize("@ss.hasPermi('scrap_records:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(VemScrapRecords vemScrapRecords)
    {
        startPage();
        List<VemScrapRecords> list = vemScrapRecordsService.selectVemScrapRecordsList(vemScrapRecords);
        return getDataTable(list);
    }

    /**
     * 导出售货机报废列表
     */
    @PreAuthorize("@ss.hasPermi('scrap_records:records:export')")
    @Log(title = "售货机报废", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VemScrapRecords vemScrapRecords)
    {
        List<VemScrapRecords> list = vemScrapRecordsService.selectVemScrapRecordsList(vemScrapRecords);
        ExcelUtil<VemScrapRecords> util = new ExcelUtil<VemScrapRecords>(VemScrapRecords.class);
        util.exportExcel(response, list, "售货机报废数据");
    }

    /**
     * 获取售货机报废详细信息
     */
    @PreAuthorize("@ss.hasPermi('scrap_records:records:query')")
    @GetMapping(value = "/{scrapId}")
    public AjaxResult getInfo(@PathVariable("scrapId") Long scrapId)
    {
        return success(vemScrapRecordsService.selectVemScrapRecordsByScrapId(scrapId));
    }

    /**
     * 新增售货机报废
     */
    @PreAuthorize("@ss.hasPermi('scrap_records:records:add')")
    @Log(title = "售货机报废", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VemScrapRecords vemScrapRecords)
    {
        return toAjax(vemScrapRecordsService.insertVemScrapRecords(vemScrapRecords));
    }

    /**
     * 修改售货机报废
     */
    @PreAuthorize("@ss.hasPermi('scrap_records:records:edit')")
    @Log(title = "售货机报废", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VemScrapRecords vemScrapRecords)
    {
        return toAjax(vemScrapRecordsService.updateVemScrapRecords(vemScrapRecords));
    }

    /**
     * 删除售货机报废
     */
    @PreAuthorize("@ss.hasPermi('scrap_records:records:remove')")
    @Log(title = "售货机报废", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scrapIds}")
    public AjaxResult remove(@PathVariable Long[] scrapIds)
    {
        return toAjax(vemScrapRecordsService.deleteVemScrapRecordsByScrapIds(scrapIds));
    }
}
