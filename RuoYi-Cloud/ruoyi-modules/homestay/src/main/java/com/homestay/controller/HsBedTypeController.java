package com.homestay.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.homestay.domain.HsBedType;
import com.homestay.service.IHsBedTypeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 民宿床类型Controller
 * 
 * @author paru
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/type")
public class HsBedTypeController extends BaseController
{
    @Autowired
    private IHsBedTypeService hsBedTypeService;

    /**
     * 查询民宿床类型列表
     */
    @RequiresPermissions("homestay:type:list")
    @GetMapping("/list")
    public TableDataInfo list(HsBedType hsBedType)
    {
        startPage();
        List<HsBedType> list = hsBedTypeService.selectHsBedTypeList(hsBedType);
        return getDataTable(list);
    }

    /**
     * 导出民宿床类型列表
     */
    @RequiresPermissions("homestay:type:export")
    @Log(title = "民宿床类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsBedType hsBedType)
    {
        List<HsBedType> list = hsBedTypeService.selectHsBedTypeList(hsBedType);
        ExcelUtil<HsBedType> util = new ExcelUtil<HsBedType>(HsBedType.class);
        util.exportExcel(response, list, "民宿床类型数据");
    }

    /**
     * 获取民宿床类型详细信息
     */
    @RequiresPermissions("homestay:type:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsBedTypeService.selectHsBedTypeById(id));
    }

    /**
     * 新增民宿床类型
     */
    @RequiresPermissions("homestay:type:add")
    @Log(title = "民宿床类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HsBedType hsBedType)
    {
        return toAjax(hsBedTypeService.insertHsBedType(hsBedType));
    }

    /**
     * 修改民宿床类型
     */
    @RequiresPermissions("homestay:type:edit")
    @Log(title = "民宿床类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsBedType hsBedType)
    {
        return toAjax(hsBedTypeService.updateHsBedType(hsBedType));
    }

    /**
     * 删除民宿床类型
     */
    @RequiresPermissions("homestay:type:remove")
    @Log(title = "民宿床类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsBedTypeService.deleteHsBedTypeByIds(ids));
    }
}
