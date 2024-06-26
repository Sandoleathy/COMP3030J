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
import com.homestay.domain.HsGuest;
import com.homestay.service.IHsGuestService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * B&B customer formController
 * 
 * @author paru
 * @date 2024-04-09
 */
@RestController
@RequestMapping("/guest")
public class HsGuestController extends BaseController
{
    @Autowired
    private IHsGuestService hsGuestService;

    /**
     * 查询B&B customer form列表
     */
    @RequiresPermissions("homestay:guest:list")
    @GetMapping("/list")
    public TableDataInfo list(HsGuest hsGuest)
    {
        startPage();
        List<HsGuest> list = hsGuestService.selectHsGuestList(hsGuest);
        return getDataTable(list);
    }

    /**
     * 导出B&B customer form列表
     */
    @RequiresPermissions("homestay:guest:export")
    @Log(title = "B&B customer form", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsGuest hsGuest)
    {
        List<HsGuest> list = hsGuestService.selectHsGuestList(hsGuest);
        ExcelUtil<HsGuest> util = new ExcelUtil<HsGuest>(HsGuest.class);
        util.exportExcel(response, list, "B&B customer form数据");
    }

    /**
     * 获取B&B customer form详细信息
     */
    @RequiresPermissions("homestay:guest:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsGuestService.selectHsGuestById(id));
    }

    /**
     * 根据预订ID查询B&B customer form列表
     */
    @RequiresPermissions("homestay:guest:queryByReservationId")
    @GetMapping(value = "/queryByReservationId/{reservationId}")
    public AjaxResult getInfoByReservationId(@PathVariable("reservationId") Long id)
    {
        return success(hsGuestService.selectSysUsersByReservationId(id));
    }

    /**
     * 根据用户ID查询预订信息
     */
    @RequiresPermissions("homestay:guest:queryByUserId")
    @GetMapping(value = "/queryByUserId/{userId}")
    public AjaxResult getInfoByUserId(@PathVariable("userId") Long id)
    {
        return success(hsGuestService.selectReservationByUserId(id));
    }

    /**
     * 新增B&B customer form
     */
    @RequiresPermissions("homestay:guest:add")
    @Log(title = "B&B customer form", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody HsGuest hsGuest)
    {
        return toAjax(hsGuestService.insertHsGuest(hsGuest));
    }

    /**
     * 修改B&B customer form
     */
    @RequiresPermissions("homestay:guest:edit")
    @Log(title = "B&B customer form", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsGuest hsGuest)
    {
        return toAjax(hsGuestService.updateHsGuest(hsGuest));
    }

    /**
     * 删除B&B customer form
     */
    @RequiresPermissions("homestay:guest:remove")
    @Log(title = "B&B customer form", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsGuestService.deleteHsGuestByIds(ids));
    }
}
