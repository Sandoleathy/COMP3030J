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
import com.homestay.domain.HsReservation;
import com.homestay.service.IHsReservationService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 房间预定Controller
 * 
 * @author ruoyi
 * @date 2024-03-26
 */
@RestController
@RequestMapping("/reservation")
public class HsReservationController extends BaseController
{
    @Autowired
    private IHsReservationService hsReservationService;

    /**
     * 查询房间预定列表
     */
    @RequiresPermissions("homestay:reservation:list")
    @GetMapping("/list")
    public TableDataInfo list(HsReservation hsReservation)
    {
        startPage();
        List<HsReservation> list = hsReservationService.selectHsReservationList(hsReservation);
        return getDataTable(list);
    }

    /**
     * 导出房间预定列表
     */
    @RequiresPermissions("homestay:reservation:export")
    @Log(title = "房间预定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsReservation hsReservation)
    {
        List<HsReservation> list = hsReservationService.selectHsReservationList(hsReservation);
        ExcelUtil<HsReservation> util = new ExcelUtil<HsReservation>(HsReservation.class);
        util.exportExcel(response, list, "房间预定数据");
    }

    /**
     * 获取房间预定详细信息
     */
    @RequiresPermissions("homestay:reservation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsReservationService.selectHsReservationById(id));
    }

    /**
     * 新增房间预定
     */
    @RequiresPermissions("homestay:reservation:add")
    @Log(title = "房间预定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HsReservation hsReservation)
    {
        return toAjax(hsReservationService.insertHsReservation(hsReservation));
    }

    /**
     * 修改房间预定
     */
    @RequiresPermissions("homestay:reservation:edit")
    @Log(title = "房间预定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsReservation hsReservation)
    {
        return toAjax(hsReservationService.updateHsReservation(hsReservation));
    }

    /**
     * 删除房间预定
     */
    @RequiresPermissions("homestay:reservation:remove")
    @Log(title = "房间预定", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsReservationService.deleteHsReservationByIds(ids));
    }
}
