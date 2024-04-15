package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsBedImage;

/**
 * 床图片Mapper接口
 * 
 * @author paru
 * @date 2024-04-14
 */
public interface HsBedImageMapper 
{
    /**
     * 查询床图片
     * 
     * @param id 床图片主键
     * @return 床图片
     */
    public HsBedImage selectHsBedImageById(Long id);

    /**
     * 查询床图片列表
     * 
     * @param hsBedImage 床图片
     * @return 床图片集合
     */
    public List<HsBedImage> selectHsBedImageList(HsBedImage hsBedImage);

    /**
     * 新增床图片
     * 
     * @param hsBedImage 床图片
     * @return 结果
     */
    public int insertHsBedImage(HsBedImage hsBedImage);

    /**
     * 修改床图片
     * 
     * @param hsBedImage 床图片
     * @return 结果
     */
    public int updateHsBedImage(HsBedImage hsBedImage);

    /**
     * 删除床图片
     * 
     * @param id 床图片主键
     * @return 结果
     */
    public int deleteHsBedImageById(Long id);

    /**
     * 批量删除床图片
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsBedImageByIds(Long[] ids);

    /**
     * Fetches all bed images associated with a given bed ID.
     *
     * @param bedId the bed ID to search for
     * @return a list of HsBedImage objects
     */
    List<HsBedImage> selectHsBedImageByBedId(Long bedId);
}
