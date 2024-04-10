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
import com.homestay.domain.HsRoom;
import com.homestay.service.IHsRoomService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 民宿房间Controller
 * 
 * @author paru
 * @date 2024-04-09
 */
@RestController
@RequestMapping("/room")
public class HsRoomController extends BaseController
{
    @Autowired
    private IHsRoomService hsRoomService;

    /**
     * 查询民宿房间列表
     */
    @RequiresPermissions("homestay:room:list")
    @GetMapping("/list")
    public TableDataInfo list(HsRoom hsRoom)
    {
        startPage();
        List<HsRoom> list = hsRoomService.selectHsRoomList(hsRoom);
        return getDataTable(list);
    }

    /**
     * 导出民宿房间列表
     */
    @RequiresPermissions("homestay:room:export")
    @Log(title = "民宿房间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsRoom hsRoom)
    {
        List<HsRoom> list = hsRoomService.selectHsRoomList(hsRoom);
        ExcelUtil<HsRoom> util = new ExcelUtil<HsRoom>(HsRoom.class);
        util.exportExcel(response, list, "民宿房间数据");
    }

    /**
     * 获取民宿房间详细信息
     */
    @RequiresPermissions("homestay:room:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsRoomService.selectHsRoomById(id));
    }

    /**
     * 新增民宿房间
     */
    @RequiresPermissions("homestay:room:add")
    @Log(title = "民宿房间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HsRoom hsRoom)
    {
        return toAjax(hsRoomService.insertHsRoom(hsRoom));
    }

    /**
     * 修改民宿房间
     */
    @RequiresPermissions("homestay:room:edit")
    @Log(title = "民宿房间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsRoom hsRoom)
    {
        return toAjax(hsRoomService.updateHsRoom(hsRoom));
    }

    /**
     * 删除民宿房间
     */
    @RequiresPermissions("homestay:room:remove")
    @Log(title = "民宿房间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsRoomService.deleteHsRoomByIds(ids));
    }
}
