package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsBuildingTypeMapper;
import com.homestay.domain.HsBuildingType;
import com.homestay.service.IHsBuildingTypeService;

/**
 * 民宿栋类型Service业务层处理
 * 
 * @author paru
 * @date 2024-04-09
 */
@Service
public class HsBuildingTypeServiceImpl implements IHsBuildingTypeService 
{
    @Autowired
    private HsBuildingTypeMapper hsBuildingTypeMapper;

    /**
     * 查询民宿栋类型
     * 
     * @param id 民宿栋类型主键
     * @return 民宿栋类型
     */
    @Override
    public HsBuildingType selectHsBuildingTypeById(Long id)
    {
        return hsBuildingTypeMapper.selectHsBuildingTypeById(id);
    }

    /**
     * 查询民宿栋类型列表
     * 
     * @param hsBuildingType 民宿栋类型
     * @return 民宿栋类型
     */
    @Override
    public List<HsBuildingType> selectHsBuildingTypeList(HsBuildingType hsBuildingType)
    {
        return hsBuildingTypeMapper.selectHsBuildingTypeList(hsBuildingType);
    }

    /**
     * 新增民宿栋类型
     * 
     * @param hsBuildingType 民宿栋类型
     * @return 结果
     */
    @Override
    public int insertHsBuildingType(HsBuildingType hsBuildingType)
    {
        return hsBuildingTypeMapper.insertHsBuildingType(hsBuildingType);
    }

    /**
     * 修改民宿栋类型
     * 
     * @param hsBuildingType 民宿栋类型
     * @return 结果
     */
    @Override
    public int updateHsBuildingType(HsBuildingType hsBuildingType)
    {
        return hsBuildingTypeMapper.updateHsBuildingType(hsBuildingType);
    }

    /**
     * 批量删除民宿栋类型
     * 
     * @param ids 需要删除的民宿栋类型主键
     * @return 结果
     */
    @Override
    public int deleteHsBuildingTypeByIds(Long[] ids)
    {
        return hsBuildingTypeMapper.deleteHsBuildingTypeByIds(ids);
    }

    /**
     * 删除民宿栋类型信息
     * 
     * @param id 民宿栋类型主键
     * @return 结果
     */
    @Override
    public int deleteHsBuildingTypeById(Long id)
    {
        return hsBuildingTypeMapper.deleteHsBuildingTypeById(id);
    }
}
