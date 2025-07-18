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
import com.ruoyi.vemSys.domain.VemRepairRecords;
import com.ruoyi.vemSys.service.IVemRepairRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售货机维修记录Controller
 * 
 * @author ruoyi
 * @date 2025-03-12
 */
@RestController
@RequestMapping("/repair_records/repairRecords")
public class VemRepairRecordsController extends BaseController
{
    @Autowired
    private IVemRepairRecordsService vemRepairRecordsService;

    /**
     * 查询售货机维修记录列表
     */
    @PreAuthorize("@ss.hasPermi('repair_records:repairRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(VemRepairRecords vemRepairRecords)
    {
        startPage();
        List<VemRepairRecords> list = vemRepairRecordsService.selectVemRepairRecordsList(vemRepairRecords);
        return getDataTable(list);
    }

    /**
     * 导出售货机维修记录列表
     */
    @PreAuthorize("@ss.hasPermi('repair_records:repairRecords:export')")
    @Log(title = "售货机维修记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VemRepairRecords vemRepairRecords)
    {
        List<VemRepairRecords> list = vemRepairRecordsService.selectVemRepairRecordsList(vemRepairRecords);
        ExcelUtil<VemRepairRecords> util = new ExcelUtil<VemRepairRecords>(VemRepairRecords.class);
        util.exportExcel(response, list, "售货机维修记录数据");
    }

    /**
     * 获取售货机维修记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('repair_records:repairRecords:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(vemRepairRecordsService.selectVemRepairRecordsByRecordId(recordId));
    }

    /**
     * 新增售货机维修记录
     */
    @PreAuthorize("@ss.hasPermi('repair_records:repairRecords:add')")
    @Log(title = "售货机维修记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VemRepairRecords vemRepairRecords)
    {
        return toAjax(vemRepairRecordsService.insertVemRepairRecords(vemRepairRecords));
    }

    /**
     * 修改售货机维修记录
     */
    @PreAuthorize("@ss.hasPermi('repair_records:repairRecords:edit')")
    @Log(title = "售货机维修记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VemRepairRecords vemRepairRecords)
    {
        return toAjax(vemRepairRecordsService.updateVemRepairRecords(vemRepairRecords));
    }

    /**
     * 删除售货机维修记录
     */
    @PreAuthorize("@ss.hasPermi('repair_records:repairRecords:remove')")
    @Log(title = "售货机维修记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(vemRepairRecordsService.deleteVemRepairRecordsByRecordIds(recordIds));
    }
}
