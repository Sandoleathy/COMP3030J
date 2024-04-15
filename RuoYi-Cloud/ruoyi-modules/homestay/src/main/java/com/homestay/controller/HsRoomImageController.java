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
import com.homestay.domain.HsRoomImage;
import com.homestay.service.IHsRoomImageService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 房间图片Controller
 * 
 * @author paru
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/roomImage")
public class HsRoomImageController extends BaseController
{
    @Autowired
    private IHsRoomImageService hsRoomImageService;

    /**
     * 查询房间图片列表
     */
    @RequiresPermissions("homestay:roomImage:list")
    @GetMapping("/list")
    public TableDataInfo list(HsRoomImage hsRoomImage)
    {
        startPage();
        List<HsRoomImage> list = hsRoomImageService.selectHsRoomImageList(hsRoomImage);
        return getDataTable(list);
    }

    /**
     * 导出房间图片列表
     */
    @RequiresPermissions("homestay:roomImage:export")
    @Log(title = "房间图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsRoomImage hsRoomImage)
    {
        List<HsRoomImage> list = hsRoomImageService.selectHsRoomImageList(hsRoomImage);
        ExcelUtil<HsRoomImage> util = new ExcelUtil<HsRoomImage>(HsRoomImage.class);
        util.exportExcel(response, list, "房间图片数据");
    }

    /**
     * 获取房间图片详细信息
     */
    @RequiresPermissions("homestay:roomImage:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsRoomImageService.selectHsRoomImageById(id));
    }

    /**
     * 新增房间图片
     */
    @RequiresPermissions("homestay:roomImage:add")
    @Log(title = "房间图片", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody HsRoomImage hsRoomImage)
    {
        return toAjax(hsRoomImageService.insertHsRoomImage(hsRoomImage));
    }

    /**
     * 修改房间图片
     */
    @RequiresPermissions("homestay:roomImage:edit")
    @Log(title = "房间图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsRoomImage hsRoomImage)
    {
        return toAjax(hsRoomImageService.updateHsRoomImage(hsRoomImage));
    }

    /**
     * 删除房间图片
     */
    @RequiresPermissions("homestay:roomImage:remove")
    @Log(title = "房间图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsRoomImageService.deleteHsRoomImageByIds(ids));
    }
}
