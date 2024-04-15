package com.homestay.service;

import java.util.List;
import com.homestay.domain.HsRoomImage;

/**
 * 房间图片Service接口
 * 
 * @author paru
 * @date 2024-04-14
 */
public interface IHsRoomImageService 
{
    /**
     * 查询房间图片
     * 
     * @param id 房间图片主键
     * @return 房间图片
     */
    public HsRoomImage selectHsRoomImageById(Long id);

    /**
     * 查询房间图片列表
     * 
     * @param hsRoomImage 房间图片
     * @return 房间图片集合
     */
    public List<HsRoomImage> selectHsRoomImageList(HsRoomImage hsRoomImage);

    /**
     * 新增房间图片
     * 
     * @param hsRoomImage 房间图片
     * @return 结果
     */
    public int insertHsRoomImage(HsRoomImage hsRoomImage);

    /**
     * 修改房间图片
     * 
     * @param hsRoomImage 房间图片
     * @return 结果
     */
    public int updateHsRoomImage(HsRoomImage hsRoomImage);

    /**
     * 批量删除房间图片
     * 
     * @param ids 需要删除的房间图片主键集合
     * @return 结果
     */
    public int deleteHsRoomImageByIds(Long[] ids);

    /**
     * 删除房间图片信息
     * 
     * @param id 房间图片主键
     * @return 结果
     */
    public int deleteHsRoomImageById(Long id);
}
