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
import com.homestay.domain.HsBedImage;
import com.homestay.service.IHsBedImageService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 床图片Controller
 * 
 * @author paru
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/bedImage")
public class HsBedImageController extends BaseController
{
    @Autowired
    private IHsBedImageService hsBedImageService;

    /**
     * 查询床图片列表
     */
    @RequiresPermissions("homestay:bedImage:list")
    @GetMapping("/list")
    public TableDataInfo list(HsBedImage hsBedImage)
    {
        startPage();
        List<HsBedImage> list = hsBedImageService.selectHsBedImageList(hsBedImage);
        return getDataTable(list);
    }

    /**
     * 导出床图片列表
     */
    @RequiresPermissions("homestay:bedImage:export")
    @Log(title = "床图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsBedImage hsBedImage)
    {
        List<HsBedImage> list = hsBedImageService.selectHsBedImageList(hsBedImage);
        ExcelUtil<HsBedImage> util = new ExcelUtil<HsBedImage>(HsBedImage.class);
        util.exportExcel(response, list, "床图片数据");
    }

    /**
     * 获取床图片详细信息
     */
    @RequiresPermissions("homestay:bedImage:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(hsBedImageService.selectHsBedImageById(id));
    }

    /**
     * 新增床图片
     */
    @RequiresPermissions("homestay:bedImage:add")
    @Log(title = "床图片", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody HsBedImage hsBedImage)
    {
        return toAjax(hsBedImageService.insertHsBedImage(hsBedImage));
    }

    /**
     * 修改床图片
     */
    @RequiresPermissions("homestay:bedImage:edit")
    @Log(title = "床图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HsBedImage hsBedImage)
    {
        return toAjax(hsBedImageService.updateHsBedImage(hsBedImage));
    }

    /**
     * 删除床图片
     */
    @RequiresPermissions("homestay:bedImage:remove")
    @Log(title = "床图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hsBedImageService.deleteHsBedImageByIds(ids));
    }
}
