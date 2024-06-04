package com.homestay.controller;

import com.homestay.domain.HsBedType;
import com.homestay.dto.BedTypeDTO;
import com.homestay.dto.SelectBedTypeDTO;
import com.homestay.service.IHsBedTypeService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * B&B bed typeController
 *
 * @author paru
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/type")
public class HsBedTypeController extends BaseController {
    @Autowired
    private IHsBedTypeService hsBedTypeService;

    /**
     * 查询B&B bed type列表
     */
    @RequiresPermissions("homestay:type:list")
    @GetMapping("/list")
    public TableDataInfo list(SelectBedTypeDTO selectBedTypeDTO) {
        startPage();
        List<BedTypeDTO> list = hsBedTypeService.selectHsBedTypeList(selectBedTypeDTO);
        return getDataTable(list);
    }

    /**
     * 导出B&B bed type列表
     */
    @RequiresPermissions("homestay:type:export")
    @Log(title = "B&B bed type", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelectBedTypeDTO selectBedTypeDTO) {
        List<BedTypeDTO> list = hsBedTypeService.selectHsBedTypeList(selectBedTypeDTO);
        ExcelUtil<BedTypeDTO> util = new ExcelUtil<BedTypeDTO>(BedTypeDTO.class);
        util.exportExcel(response, list, "B&B bed type数据");
    }

    /**
     * 获取B&B bed type详细信息
     */
    @RequiresPermissions("homestay:type:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(hsBedTypeService.selectHsBedTypeById(id));
    }

    /**
     * 新增B&B bed type
     */
    @RequiresPermissions("homestay:type:add")
    @Log(title = "B&B bed type", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody HsBedType hsBedType) {
        return toAjax(hsBedTypeService.insertHsBedType(hsBedType));
    }

    /**
     * 修改B&B bed type
     */
    @RequiresPermissions("homestay:type:edit")
    @Log(title = "B&B bed type", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsBedType hsBedType) {
        return toAjax(hsBedTypeService.updateHsBedType(hsBedType));
    }

    /**
     * 删除B&B bed type
     */
    @RequiresPermissions("homestay:type:remove")
    @Log(title = "B&B bed type", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public List<AjaxResult> remove(@PathVariable Long[] ids) {
        List<AjaxResult> ajaxResults = new ArrayList<>();
        int[] results = hsBedTypeService.deleteHsBedTypeByIds(ids);
        for (int i : results) {
            ajaxResults.add(toAjax(i));
        }
        return ajaxResults;
    }
}
