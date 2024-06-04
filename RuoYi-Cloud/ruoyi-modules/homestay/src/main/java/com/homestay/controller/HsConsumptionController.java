package com.homestay.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.homestay.dto.ConsumptionDTO;
import com.homestay.dto.SelectConsumptionDTO;
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
import com.homestay.domain.HsConsumption;
import com.homestay.service.IHsConsumptionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * B&B consumptionController
 * 
 * @author paru
 * @date 2024-04-13
 */
@RestController
@RequestMapping("/consumption")
public class HsConsumptionController extends BaseController
{
    @Autowired
    private IHsConsumptionService hsConsumptionService;

    /**
     * 查询B&B consumption列表
     */
    @RequiresPermissions("homestay:consumption:list")
    @GetMapping("/list")
    public TableDataInfo list(SelectConsumptionDTO selectConsumptionDTO)
    {
        startPage();
        List<ConsumptionDTO> list = hsConsumptionService.selectHsConsumptionList(selectConsumptionDTO);
        return getDataTable(list);
    }

    /**
     * 导出B&B consumption列表
     */
    @RequiresPermissions("homestay:consumption:export")
    @Log(title = "B&B consumption", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelectConsumptionDTO selectConsumptionDTO)
    {
        List<ConsumptionDTO> list = hsConsumptionService.selectHsConsumptionList(selectConsumptionDTO);
        ExcelUtil<ConsumptionDTO> util = new ExcelUtil<ConsumptionDTO>(ConsumptionDTO.class);
        util.exportExcel(response, list, "B&B consumption数据");
    }

    /**
     * 获取B&B consumption详细信息
     */
    @RequiresPermissions("homestay:consumption:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsConsumptionService.selectHsConsumptionById(id));
    }

    /**
     * 新增B&B consumption
     */
    @RequiresPermissions("homestay:consumption:add")
    @Log(title = "B&B consumption", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody HsConsumption hsConsumption)
    {
        return toAjax(hsConsumptionService.insertHsConsumption(hsConsumption));
    }

    /**
     * 修改B&B consumption
     */
    @RequiresPermissions("homestay:consumption:edit")
    @Log(title = "B&B consumption", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsConsumption hsConsumption)
    {
        return toAjax(hsConsumptionService.updateHsConsumption(hsConsumption));
    }

    /**
     * 删除B&B consumption
     */
    @RequiresPermissions("homestay:consumption:remove")
    @Log(title = "B&B consumption", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsConsumptionService.deleteHsConsumptionByIds(ids));
    }
}
