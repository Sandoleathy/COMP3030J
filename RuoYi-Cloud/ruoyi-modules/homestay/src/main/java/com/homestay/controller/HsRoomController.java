package com.homestay.controller;

import com.homestay.domain.HsRoom;
import com.homestay.domain.HsRoomImage;
import com.homestay.dto.AddRoomDTO;
import com.homestay.dto.SelectRoomDTO;
import com.homestay.service.IHsRoomImageService;
import com.homestay.service.IHsRoomService;
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
 * 民宿房间Controller
 *
 * @author paru
 * @date 2024-04-12
 */
@RestController
@RequestMapping("/room")
public class HsRoomController extends BaseController {
    @Autowired
    private IHsRoomService hsRoomService;

    @Autowired
    private IHsRoomImageService hsRoomImageService;

    /**
     * 查询民宿房间列表
     */
    @RequiresPermissions("homestay:room:list")
    @GetMapping("/list")
    public TableDataInfo list(SelectRoomDTO selectRoomDTO) {
        startPage();
        List<AddRoomDTO> list = hsRoomService.selectHsRoomList(selectRoomDTO);
        return getDataTable(list);
    }

    /**
     * 导出民宿房间列表
     */
    @RequiresPermissions("homestay:room:export")
    @Log(title = "民宿房间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelectRoomDTO selectRoomDTO) {
        List<AddRoomDTO> list = hsRoomService.selectHsRoomList(selectRoomDTO);
        ExcelUtil<AddRoomDTO> util = new ExcelUtil<AddRoomDTO>(AddRoomDTO.class);
        util.exportExcel(response, list, "民宿房间数据");
    }

    /**
     * 获取民宿房间详细信息
     */
    @RequiresPermissions("homestay:room:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(hsRoomService.selectHsRoomById(id));
    }

    /**
     * 新增民宿房间
     */
    @RequiresPermissions("homestay:room:add")
    @Log(title = "民宿房间", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public List<AjaxResult> add(@RequestBody AddRoomDTO addRoomDTO) {
        List<AjaxResult> results = new ArrayList<>();
        HsRoom hsRoom=addRoomDTO.getHsRoom();
        hsRoomService.insertHsRoom(hsRoom);
        results.add(toAjax(hsRoom.getId().intValue()));
        List<HsRoomImage> images = addRoomDTO.getHsRoomImages();
        for (HsRoomImage image : images) {
            image.setRoomId(hsRoom.getId());
            results.add(toAjax(hsRoomImageService.insertHsRoomImage(image)));
        }
        return results;
    }

    /**
     * 修改民宿房间
     */
    @RequiresPermissions("homestay:room:edit")
    @Log(title = "民宿房间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsRoom hsRoom) {
        return toAjax(hsRoomService.updateHsRoom(hsRoom));
    }

    /**
     * 删除民宿房间
     */
    @RequiresPermissions("homestay:room:remove")
    @Log(title = "民宿房间", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public List<AjaxResult> remove(@PathVariable Long[] ids) {
        List<AjaxResult> ajaxResults = new ArrayList<>();
        int[] results = hsRoomService.deleteHsRoomByIds(ids);
        for (int i : results) {
            ajaxResults.add(toAjax(i));
        }
        return ajaxResults;
    }
}
