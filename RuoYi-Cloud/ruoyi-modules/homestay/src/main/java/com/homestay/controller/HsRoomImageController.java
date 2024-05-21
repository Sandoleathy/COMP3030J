package com.homestay.controller;

import com.homestay.domain.HsRoomImage;
import com.homestay.dto.HsRoomImageDTO;
import com.homestay.service.IHsRoomImageService;
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
 * 房间图片Controller
 *
 * @author paru
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/roomImage")
public class HsRoomImageController extends BaseController {
    @Autowired
    private IHsRoomImageService hsRoomImageService;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 查询房间图片列表
     */
    @RequiresPermissions("homestay:roomImage:list")
    @GetMapping("/list")
    public TableDataInfo list(HsRoomImage hsRoomImage) {
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
    public void export(HttpServletResponse response, HsRoomImage hsRoomImage) {
        List<HsRoomImage> list = hsRoomImageService.selectHsRoomImageList(hsRoomImage);
        ExcelUtil<HsRoomImage> util = new ExcelUtil<HsRoomImage>(HsRoomImage.class);
        util.exportExcel(response, list, "房间图片数据");
    }

    /**
     * 获取房间图片详细信息
     */
    @RequiresPermissions("homestay:roomImage:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(hsRoomImageService.selectHsRoomImageById(id));
    }

    /**
     * 新增房间图片
     */
    @RequiresPermissions("homestay:roomImage:add")
    @Log(title = "房间图片", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(HsRoomImageDTO hsRoomImageDTO) {
        MultipartFile file = hsRoomImageDTO.getImage();
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
            HsRoomImage hsRoomImage = new HsRoomImage();
            hsRoomImage.setImageDesc(hsRoomImageDTO.getImageDesc());
            hsRoomImage.setRoomId(hsRoomImageDTO.getRoomId());
            hsRoomImage.setImage(url);
            hsRoomImageService.insertHsRoomImage(hsRoomImage);
            return ajax;
        } else {
            return error("The uploaded image is abnormal. Please contact the administrator");
        }
    }

    /**
     * 修改房间图片
     */
    @RequiresPermissions("homestay:roomImage:edit")
    @Log(title = "房间图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(HsRoomImageDTO hsRoomImageDTO) {
        MultipartFile file = hsRoomImageDTO.getImage();
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
            HsRoomImage hsRoomImage = new HsRoomImage();
            hsRoomImage.setId(hsRoomImageDTO.getId());
            hsRoomImage.setImageDesc(hsRoomImageDTO.getImageDesc());
            hsRoomImage.setRoomId(hsRoomImageDTO.getRoomId());
            hsRoomImage.setImage(url);
            hsRoomImageService.updateHsRoomImage(hsRoomImage);
            return ajax;
        } else {
            return error("The uploaded image is abnormal. Please contact the administrator");
        }
    }

    /**
     * 删除房间图片
     */
    @RequiresPermissions("homestay:roomImage:remove")
    @Log(title = "房间图片", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(hsRoomImageService.deleteHsRoomImageByIds(ids));
    }
}
