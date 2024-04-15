package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsBedImageMapper;
import com.homestay.domain.HsBedImage;
import com.homestay.service.IHsBedImageService;

/**
 * 床图片Service业务层处理
 * 
 * @author paru
 * @date 2024-04-14
 */
@Service
public class HsBedImageServiceImpl implements IHsBedImageService 
{
    @Autowired
    private HsBedImageMapper hsBedImageMapper;

    /**
     * 查询床图片
     * 
     * @param id 床图片主键
     * @return 床图片
     */
    @Override
    public HsBedImage selectHsBedImageById(Long id)
    {
        return hsBedImageMapper.selectHsBedImageById(id);
    }

    /**
     * 查询床图片列表
     * 
     * @param hsBedImage 床图片
     * @return 床图片
     */
    @Override
    public List<HsBedImage> selectHsBedImageList(HsBedImage hsBedImage)
    {
        return hsBedImageMapper.selectHsBedImageList(hsBedImage);
    }

    /**
     * 新增床图片
     * 
     * @param hsBedImage 床图片
     * @return 结果
     */
    @Override
    public int insertHsBedImage(HsBedImage hsBedImage)
    {
        return hsBedImageMapper.insertHsBedImage(hsBedImage);
    }

    /**
     * 修改床图片
     * 
     * @param hsBedImage 床图片
     * @return 结果
     */
    @Override
    public int updateHsBedImage(HsBedImage hsBedImage)
    {
        return hsBedImageMapper.updateHsBedImage(hsBedImage);
    }

    /**
     * 批量删除床图片
     * 
     * @param ids 需要删除的床图片主键
     * @return 结果
     */
    @Override
    public int deleteHsBedImageByIds(Long[] ids)
    {
        return hsBedImageMapper.deleteHsBedImageByIds(ids);
    }

    /**
     * 删除床图片信息
     * 
     * @param id 床图片主键
     * @return 结果
     */
    @Override
    public int deleteHsBedImageById(Long id)
    {
        return hsBedImageMapper.deleteHsBedImageById(id);
    }
}
