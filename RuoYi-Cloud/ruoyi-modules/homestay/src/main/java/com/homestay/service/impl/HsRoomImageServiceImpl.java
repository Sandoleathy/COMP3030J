package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsRoomImageMapper;
import com.homestay.domain.HsRoomImage;
import com.homestay.service.IHsRoomImageService;

/**
 * 房间图片Service业务层处理
 * 
 * @author paru
 * @date 2024-04-14
 */
@Service
public class HsRoomImageServiceImpl implements IHsRoomImageService 
{
    @Autowired
    private HsRoomImageMapper hsRoomImageMapper;

    /**
     * 查询房间图片
     * 
     * @param id 房间图片主键
     * @return 房间图片
     */
    @Override
    public HsRoomImage selectHsRoomImageById(Long id)
    {
        return hsRoomImageMapper.selectHsRoomImageById(id);
    }

    /**
     * 查询房间图片列表
     * 
     * @param hsRoomImage 房间图片
     * @return 房间图片
     */
    @Override
    public List<HsRoomImage> selectHsRoomImageList(HsRoomImage hsRoomImage)
    {
        return hsRoomImageMapper.selectHsRoomImageList(hsRoomImage);
    }

    /**
     * 新增房间图片
     * 
     * @param hsRoomImage 房间图片
     * @return 结果
     */
    @Override
    public int insertHsRoomImage(HsRoomImage hsRoomImage)
    {
        return hsRoomImageMapper.insertHsRoomImage(hsRoomImage);
    }

    /**
     * 修改房间图片
     * 
     * @param hsRoomImage 房间图片
     * @return 结果
     */
    @Override
    public int updateHsRoomImage(HsRoomImage hsRoomImage)
    {
        return hsRoomImageMapper.updateHsRoomImage(hsRoomImage);
    }

    /**
     * 批量删除房间图片
     * 
     * @param ids 需要删除的房间图片主键
     * @return 结果
     */
    @Override
    public int deleteHsRoomImageByIds(Long[] ids)
    {
        return hsRoomImageMapper.deleteHsRoomImageByIds(ids);
    }

    /**
     * 删除房间图片信息
     * 
     * @param id 房间图片主键
     * @return 结果
     */
    @Override
    public int deleteHsRoomImageById(Long id)
    {
        return hsRoomImageMapper.deleteHsRoomImageById(id);
    }
}
