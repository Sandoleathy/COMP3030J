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
 * B&B roomController
 *
 * @author paru
 * @date 2024-04-12
 */
@RestController
@RequestMapping("/room")
public class HsRoomController extends BaseController {
    @Autowired
    private IHsRoomService hsRoomService;

    /**
     * 查询B&B room列表
     */
    @RequiresPermissions("homestay:room:list")
    @GetMapping("/list")
    public TableDataInfo list(SelectRoomDTO selectRoomDTO) {
        startPage();
        List<AddRoomDTO> list = hsRoomService.selectHsRoomList(selectRoomDTO);
        return getDataTable(list);
    }

    /**
     * 导出B&B room列表
     */
    @RequiresPermissions("homestay:room:export")
    @Log(title = "B&B room", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SelectRoomDTO selectRoomDTO) {
        List<AddRoomDTO> list = hsRoomService.selectHsRoomList(selectRoomDTO);
        ExcelUtil<AddRoomDTO> util = new ExcelUtil<AddRoomDTO>(AddRoomDTO.class);
        util.exportExcel(response, list, "B&B room数据");
    }

    /**
     * 获取B&B room详细信息
     */
    @RequiresPermissions("homestay:room:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(hsRoomService.selectHsRoomById(id));
    }

    /**
     * 新增B&B room
     */
    @RequiresPermissions("homestay:room:add")
    @Log(title = "B&B room", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody HsRoom hsRoom) {
        return toAjax(hsRoomService.insertHsRoom(hsRoom));
    }

    /**
     * 修改B&B room
     */
    @RequiresPermissions("homestay:room:edit")
    @Log(title = "B&B room", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsRoom hsRoom) {
        return toAjax(hsRoomService.updateHsRoom(hsRoom));
    }

    /**
     * 删除B&B room
     */
    @RequiresPermissions("homestay:room:remove")
    @Log(title = "B&B room", businessType = BusinessType.DELETE)
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
