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
import com.homestay.domain.HsConsumption;
import com.homestay.service.IHsConsumptionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 民宿消费Controller
 * 
 * @author paru
 * @date 2024-04-09
 */
@RestController
@RequestMapping("/consumption")
public class HsConsumptionController extends BaseController
{
    @Autowired
    private IHsConsumptionService hsConsumptionService;

    /**
     * 查询民宿消费列表
     */
    @RequiresPermissions("homestay:consumption:list")
    @GetMapping("/list")
    public TableDataInfo list(HsConsumption hsConsumption)
    {
        startPage();
        List<HsConsumption> list = hsConsumptionService.selectHsConsumptionList(hsConsumption);
        return getDataTable(list);
    }

    /**
     * 导出民宿消费列表
     */
    @RequiresPermissions("homestay:consumption:export")
    @Log(title = "民宿消费", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsConsumption hsConsumption)
    {
        List<HsConsumption> list = hsConsumptionService.selectHsConsumptionList(hsConsumption);
        ExcelUtil<HsConsumption> util = new ExcelUtil<HsConsumption>(HsConsumption.class);
        util.exportExcel(response, list, "民宿消费数据");
    }

    /**
     * 获取民宿消费详细信息
     */
    @RequiresPermissions("homestay:consumption:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsConsumptionService.selectHsConsumptionById(id));
    }

    /**
     * 新增民宿消费
     */
    @RequiresPermissions("homestay:consumption:add")
    @Log(title = "民宿消费", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HsConsumption hsConsumption)
    {
        return toAjax(hsConsumptionService.insertHsConsumption(hsConsumption));
    }

    /**
     * 修改民宿消费
     */
    @RequiresPermissions("homestay:consumption:edit")
    @Log(title = "民宿消费", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsConsumption hsConsumption)
    {
        return toAjax(hsConsumptionService.updateHsConsumption(hsConsumption));
    }

    /**
     * 删除民宿消费
     */
    @RequiresPermissions("homestay:consumption:remove")
    @Log(title = "民宿消费", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsConsumptionService.deleteHsConsumptionByIds(ids));
    }
}
