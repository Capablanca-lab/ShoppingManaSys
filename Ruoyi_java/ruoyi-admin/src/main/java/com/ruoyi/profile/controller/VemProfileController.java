package com.ruoyi.profile.controller;

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
import com.ruoyi.profile.domain.VemProfile;
import com.ruoyi.profile.service.IVemProfileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售货机利润Controller
 * 
 * @author ruoyi
 * @date 2025-02-17
 */
@RestController
@RequestMapping("/total_profile/profile")
public class VemProfileController extends BaseController
{
    @Autowired
    private IVemProfileService vemProfileService;

    /**
     * 查询售货机利润列表
     */
    @PreAuthorize("@ss.hasPermi('total_profile:profile:list')")
    @GetMapping("/list")
    public TableDataInfo list(VemProfile vemProfile)
    {
        startPage();
        List<VemProfile> list = vemProfileService.selectVemProfileList(vemProfile);
        return getDataTable(list);
    }

    /**
     * 导出售货机利润列表
     */
    @PreAuthorize("@ss.hasPermi('total_profile:profile:export')")
    @Log(title = "售货机利润", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VemProfile vemProfile)
    {
        List<VemProfile> list = vemProfileService.selectVemProfileList(vemProfile);
        ExcelUtil<VemProfile> util = new ExcelUtil<VemProfile>(VemProfile.class);
        util.exportExcel(response, list, "售货机利润数据");
    }

    /**
     * 获取售货机利润详细信息
     */
    @PreAuthorize("@ss.hasPermi('total_profile:profile:query')")
    @GetMapping(value = "/{vemId}")
    public AjaxResult getInfo(@PathVariable("vemId") Long vemId)
    {
        return success(vemProfileService.selectVemProfileByVemId(vemId));
    }

    /**
     * 新增售货机利润
     */
    @PreAuthorize("@ss.hasPermi('total_profile:profile:add')")
    @Log(title = "售货机利润", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VemProfile vemProfile)
    {
        return toAjax(vemProfileService.insertVemProfile(vemProfile));
    }

    /**
     * 修改售货机利润
     */
    @PreAuthorize("@ss.hasPermi('total_profile:profile:edit')")
    @Log(title = "售货机利润", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VemProfile vemProfile)
    {
        return toAjax(vemProfileService.updateVemProfile(vemProfile));
    }

    /**
     * 删除售货机利润
     */
    @PreAuthorize("@ss.hasPermi('total_profile:profile:remove')")
    @Log(title = "售货机利润", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vemIds}")
    public AjaxResult remove(@PathVariable Long[] vemIds)
    {
        return toAjax(vemProfileService.deleteVemProfileByVemIds(vemIds));
    }
}
