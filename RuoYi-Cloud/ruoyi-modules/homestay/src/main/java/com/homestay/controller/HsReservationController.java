package com.homestay.controller;

import com.homestay.domain.HsGuest;
import com.homestay.domain.HsReservation;
import com.homestay.domain.HsRr;
import com.homestay.dto.AddReservationDTO;
import com.homestay.dto.ReservationDTO;
import com.homestay.service.IHsGuestService;
import com.homestay.service.IHsReservationService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * B&B bookingController
 *
 * @author paru
 * @date 2024-04-09
 */
@RestController
@RequestMapping("/reservation")
public class HsReservationController extends BaseController {
    @Autowired
    private IHsReservationService hsReservationService;

    @Autowired
    private IHsRrService hsRrService;

    @Autowired
    private IHsGuestService hsGuestService;

    /**
     * 查询B&B booking列表
     */
    @RequiresPermissions("homestay:reservation:list")
    @GetMapping("/list")
    public TableDataInfo list(HsReservation hsReservation) {
        startPage();
        List<ReservationDTO> reservationDTOList = hsReservationService.select(hsReservation);
        return getDataTable(reservationDTOList);
    }

    /**
     * 导出B&B booking列表
     */
    @RequiresPermissions("homestay:reservation:export")
    @Log(title = "B&B booking", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsReservation hsReservation) {
        List<ReservationDTO> list = hsReservationService.select(hsReservation);
        ExcelUtil<ReservationDTO> util = new ExcelUtil<ReservationDTO>(ReservationDTO.class);
        util.exportExcel(response, list, "B&B booking数据");
    }

    /**
     * 获取B&B booking详细信息
     */
    @RequiresPermissions("homestay:reservation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(hsReservationService.selectHsReservationById(id));
    }

    /**
     * 新增B&B booking
     */
    @RequiresPermissions("homestay:reservation:add")
    @Log(title = "B&B booking", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public List<AjaxResult> add(@RequestBody AddReservationDTO addReservationDTO) {
        List<AjaxResult> results = new ArrayList<>();
        HsReservation hsReservation = addReservationDTO.getHsReservation();
        Long[] rooms = addReservationDTO.getRoomIds();
        Long[] users = addReservationDTO.getUserIds();

        results.add(toAjax(hsReservationService.insertHsReservation(hsReservation)));
        if (results.get(0).isSuccess()) {
            for (Long room : rooms) {
                HsRr hsRr = new HsRr();
                hsRr.setReservationId(hsReservation.getId());
                hsRr.setRoomId(room);
                results.add(toAjax(hsRrService.insertHsRr(hsRr)));
            }
            for (Long user : users) {
                HsGuest hsGuest = new HsGuest();
                hsGuest.setReservationId(hsReservation.getId());
                hsGuest.setGuestId(user);
                results.add(toAjax(hsGuestService.insertHsGuest(hsGuest)));
            }
        }
        return results;
    }


    /**
     * 修改B&B booking
     */
    @RequiresPermissions("homestay:reservation:edit")
    @Log(title = "B&B booking", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsReservation hsReservation) {
        return toAjax(hsReservationService.updateHsReservation(hsReservation));
    }

    /**
     * 删除B&B booking
     */
    @RequiresPermissions("homestay:reservation:remove")
    @Log(title = "B&B booking", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public List<AjaxResult> remove(@PathVariable Long[] ids) {
        List<AjaxResult> results = new ArrayList<>();
        int[] deletes = hsReservationService.deleteHsReservationByIds(ids);
        for (int i : deletes) {
            results.add(toAjax(i));
        }
        return results;
    }

    /**
     * 支付订单
     */
    @RequiresPermissions("homestay:reservation:pay")
    @Log(title = "B&B booking", businessType = BusinessType.UPDATE)
    @PostMapping("/pay")
    public AjaxResult updateReservationStatusToPaid(Long id, String description) {
        return toAjax(hsReservationService.updateReservationStatusToPaid(id, description));
    }
}
