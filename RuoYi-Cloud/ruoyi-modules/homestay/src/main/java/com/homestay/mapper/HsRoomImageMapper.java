package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsRoomImage;

/**
 * 房间图片Mapper接口
 * 
 * @author paru
 * @date 2024-04-14
 */
public interface HsRoomImageMapper 
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
     * 删除房间图片
     * 
     * @param id 房间图片主键
     * @return 结果
     */
    public int deleteHsRoomImageById(Long id);

    /**
     * 批量删除房间图片
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsRoomImageByIds(Long[] ids);

    /**
     * Fetches all room images associated with a given room ID.
     *
     * @param roomId the room ID to search for
     * @return a list of HsRoomImage objects
     */
    List<HsRoomImage> selectHsRoomImageByRoomId(Long roomId);
}
