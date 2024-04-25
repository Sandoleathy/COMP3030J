package com.homestay.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.homestay.dto.HsStaffDTO;
import com.homestay.dto.SelectStaffDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.homestay.domain.HsStaff;
import com.homestay.service.IHsStaffService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 民宿员工表Controller
 * 
 * @author paru
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/staff")
public class HsStaffController extends BaseController
{
    @Autowired
    private IHsStaffService hsStaffService;

    /**
     * 查询民宿员工表列表
     */
    @RequiresPermissions("homestay:staff:list")
    @GetMapping("/list")
    public TableDataInfo list(SelectStaffDTO hsStaff)
    {
        startPage();
        List<HsStaffDTO> list = hsStaffService.selectHsStaffList(hsStaff);
        return getDataTable(list);
    }

    /**
     * 导出民宿员工表列表
     */
    @RequiresPermissions("homestay:staff:export")
    @Log(title = "民宿员工表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelectStaffDTO hsStaff)
    {
        List<HsStaffDTO> list = hsStaffService.selectHsStaffList(hsStaff);
        ExcelUtil<HsStaffDTO> util = new ExcelUtil<HsStaffDTO>(HsStaffDTO.class);
        util.exportExcel(response, list, "民宿员工表数据");
    }

    /**
     * 获取民宿员工表详细信息
     */
    @RequiresPermissions("homestay:staff:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsStaffService.selectHsStaffById(id));
    }

    /**
     * 新增民宿员工表
     */
    @RequiresPermissions("homestay:staff:add")
    @Log(title = "民宿员工表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HsStaffDTO hsStaffDTO)
    {
        return toAjax(hsStaffService.insertHsStaff(hsStaffDTO));
    }

    /**
     * 修改民宿员工表
     */
    @RequiresPermissions("homestay:staff:edit")
    @Log(title = "民宿员工表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsStaff hsStaff)
    {
        return toAjax(hsStaffService.updateHsStaff(hsStaff));
    }

    /**
     * 删除民宿员工表
     */
    @RequiresPermissions("homestay:staff:remove")
    @Log(title = "民宿员工表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsStaffService.deleteHsStaffByIds(ids));
    }
}
