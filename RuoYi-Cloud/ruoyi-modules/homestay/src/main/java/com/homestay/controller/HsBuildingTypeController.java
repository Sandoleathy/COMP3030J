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
import com.homestay.domain.HsBuildingType;
import com.homestay.service.IHsBuildingTypeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * B&B building typeController
 * 
 * @author paru
 * @date 2024-04-09
 */
@RestController
@RequestMapping("/buildingType")
public class HsBuildingTypeController extends BaseController
{
    @Autowired
    private IHsBuildingTypeService hsBuildingTypeService;

    /**
     * 查询B&B building type列表
     */
    @RequiresPermissions("homestay:buildingType:list")
    @GetMapping("/list")
    public TableDataInfo list(HsBuildingType hsBuildingType)
    {
        startPage();
        List<HsBuildingType> list = hsBuildingTypeService.selectHsBuildingTypeList(hsBuildingType);
        return getDataTable(list);
    }

    /**
     * 导出B&B building type列表
     */
    @RequiresPermissions("homestay:buildingType:export")
    @Log(title = "B&B building type", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsBuildingType hsBuildingType)
    {
        List<HsBuildingType> list = hsBuildingTypeService.selectHsBuildingTypeList(hsBuildingType);
        ExcelUtil<HsBuildingType> util = new ExcelUtil<HsBuildingType>(HsBuildingType.class);
        util.exportExcel(response, list, "B&B building type数据");
    }

    /**
     * 获取B&B building type详细信息
     */
    @RequiresPermissions("homestay:buildingType:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsBuildingTypeService.selectHsBuildingTypeById(id));
    }

    /**
     * 新增B&B building type
     */
    @RequiresPermissions("homestay:buildingType:add")
    @Log(title = "B&B building type", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody HsBuildingType hsBuildingType)
    {
        return toAjax(hsBuildingTypeService.insertHsBuildingType(hsBuildingType));
    }

    /**
     * 修改B&B building type
     */
    @RequiresPermissions("homestay:buildingType:edit")
    @Log(title = "B&B building type", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsBuildingType hsBuildingType)
    {
        return toAjax(hsBuildingTypeService.updateHsBuildingType(hsBuildingType));
    }

    /**
     * 删除B&B building type
     */
    @RequiresPermissions("homestay:buildingType:remove")
    @Log(title = "B&B building type", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsBuildingTypeService.deleteHsBuildingTypeByIds(ids));
    }
}
