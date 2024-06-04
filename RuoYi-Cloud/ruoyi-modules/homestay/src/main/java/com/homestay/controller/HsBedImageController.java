package com.homestay.controller;

import com.homestay.domain.HsBedImage;
import com.homestay.dto.HsBedImageDTO;
import com.homestay.service.IHsBedImageService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileTypeUtils;
import com.ruoyi.common.core.utils.file.MimeTypeUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.api.RemoteFileService;
import com.ruoyi.system.api.domain.SysFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * bed picturesController
 *
 * @author paru
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/bedImage")
public class HsBedImageController extends BaseController {
    @Autowired
    private IHsBedImageService hsBedImageService;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 查询bed pictures列表
     */
    @RequiresPermissions("homestay:bedImage:list")
    @GetMapping("/list")
    public TableDataInfo list(HsBedImage hsBedImage) {
        startPage();
        List<HsBedImage> list = hsBedImageService.selectHsBedImageList(hsBedImage);
        return getDataTable(list);
    }

    /**
     * 导出bed pictures列表
     */
    @RequiresPermissions("homestay:bedImage:export")
    @Log(title = "bed pictures", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HsBedImage hsBedImage) {
        List<HsBedImage> list = hsBedImageService.selectHsBedImageList(hsBedImage);
        ExcelUtil<HsBedImage> util = new ExcelUtil<HsBedImage>(HsBedImage.class);
        util.exportExcel(response, list, "bed pictures数据");
    }

    /**
     * 获取bed pictures详细信息
     */
    @RequiresPermissions("homestay:bedImage:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(hsBedImageService.selectHsBedImageById(id));
    }

    /**
     * 新增bed pictures
     */
    @RequiresPermissions("homestay:bedImage:add")
    @Log(title = "bed pictures", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(HsBedImageDTO hsBedImageDTO) {
        MultipartFile file = hsBedImageDTO.getImage();
        if (!file.isEmpty()) {
            String extension = FileTypeUtils.getExtension(file);
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
                return error("File format is incorrect, please upload" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "format");
            }
            R<SysFile> fileResult = remoteFileService.upload(file);
            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData())) {
                return error("The file service is abnormal. Contact the administrator");
            }
            String url = fileResult.getData().getUrl();
            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", url);
            HsBedImage hsBedImage = new HsBedImage();
            hsBedImage.setImageDesc(hsBedImageDTO.getImageDesc());
            hsBedImage.setBedId(hsBedImageDTO.getBedId());
            hsBedImage.setImageData(url);
            hsBedImageService.insertHsBedImage(hsBedImage);
            return ajax;
        } else {
            return error("The uploaded image is abnormal. Please contact the administrator");
        }
    }

    /**
     * 修改bed pictures
     */
    @RequiresPermissions("homestay:bedImage:edit")
    @Log(title = "bed pictures", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(HsBedImageDTO hsBedImageDTO) {
        MultipartFile file = hsBedImageDTO.getImage();
        if (!file.isEmpty()) {
            String extension = FileTypeUtils.getExtension(file);
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
                return error("File format is incorrect, please upload" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "format");
            }
            R<SysFile> fileResult = remoteFileService.upload(file);
            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData())) {
                return error("The file service is abnormal. Contact the administrator");
            }
            String url = fileResult.getData().getUrl();
            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", url);
            HsBedImage hsBedImage = new HsBedImage();
            hsBedImage.setId(hsBedImageDTO.getId());
            hsBedImage.setImageDesc(hsBedImageDTO.getImageDesc());
            hsBedImage.setBedId(hsBedImageDTO.getBedId());
            hsBedImage.setImageData(url);
            hsBedImageService.updateHsBedImage(hsBedImage);
            return ajax;
        }
        return error("The uploaded image is abnormal. Please contact the administrator");
    }

    /**
     * 删除bed pictures
     */
    @RequiresPermissions("homestay:bedImage:remove")
    @Log(title = "bed pictures", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(hsBedImageService.deleteHsBedImageByIds(ids));
    }
}
