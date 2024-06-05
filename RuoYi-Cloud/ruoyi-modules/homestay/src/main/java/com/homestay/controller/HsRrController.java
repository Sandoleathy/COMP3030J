package com.homestay.controller;

import com.homestay.domain.HsRr;
import com.homestay.dto.HsRrDTO;
import com.homestay.service.IHsRrService;
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
import java.util.List;

/**
 * Order customer unionController
 *
 * @author paru
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/rr")
public class HsRrController extends BaseController {
    @Autowired
    private IHsRrService hsRrService;

    /**
     * 查询订单房间联合列表
     */
    @RequiresPermissions("homestay:rr:list")
    @GetMapping("/list")
    public TableDataInfo list(HsRr hsRr) {
        startPage();
        List<HsRrDTO> list = hsRrService.selectHsRrList(hsRr);
        return getDataTable(list);
    }

    /**
     * 导出订单房间联合列表
     */
    @RequiresPermissions("homestay:rr:export")
    @Log(title = "Order customer union", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsRr hsRr) {
        List<HsRrDTO> list = hsRrService.selectHsRrList(hsRr);
        ExcelUtil<HsRrDTO> util = new ExcelUtil<HsRrDTO>(HsRrDTO.class);
        util.exportExcel(response, list, "Order customer union数据");
    }

    /**
     * 获取订单房间联合详细信息
     */
    @RequiresPermissions("homestay:rr:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(hsRrService.selectHsRrById(id));
    }

    /**
     * 根据房间ID查询预订表
     */
    @RequiresPermissions("homestay:rr:queryByRoomId")
    @GetMapping(value = "/queryByRoomId/{roomId}")
    public AjaxResult getInfoByRoomId(@PathVariable("roomId") Long roomId) {

        return success(hsRrService.selectHsRrByRoomId(roomId));
    }

    /**
     * 根据预订ID查询房间表
     */
    @RequiresPermissions("homestay:rr:queryByReservationId")
    @GetMapping(value = "/queryByReservationId/{reservationId}")
    public AjaxResult getInfoByReservationId(@PathVariable("reservationId") Long reservationId) {
        return success(hsRrService.selectHsRrByReservationId(reservationId));
    }

    /**
     * 新增订单房间联合
     */
    @RequiresPermissions("homestay:rr:add")
    @Log(title = "Order customer union", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody HsRr hsRr) {
        return toAjax(hsRrService.insertHsRr(hsRr));
    }

    /**
     * 修改订单房间联合
     */
    @RequiresPermissions("homestay:rr:edit")
    @Log(title = "Order customer union", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsRr hsRr) {
        return toAjax(hsRrService.updateHsRr(hsRr));
    }

    /**
     * 删除订单房间联合
     */
    @RequiresPermissions("homestay:rr:remove")
    @Log(title = "Order customer union", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(hsRrService.deleteHsRrByIds(ids));
    }
}
