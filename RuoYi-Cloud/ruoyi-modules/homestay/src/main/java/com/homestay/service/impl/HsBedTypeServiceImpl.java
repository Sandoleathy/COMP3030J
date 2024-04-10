package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsBedTypeMapper;
import com.homestay.domain.HsBedType;
import com.homestay.service.IHsBedTypeService;

/**
 * 民宿床类型Service业务层处理
 * 
 * @author paru
 * @date 2024-04-10
 */
@Service
public class HsBedTypeServiceImpl implements IHsBedTypeService 
{
    @Autowired
    private HsBedTypeMapper hsBedTypeMapper;

    /**
     * 查询民宿床类型
     * 
     * @param id 民宿床类型主键
     * @return 民宿床类型
     */
    @Override
    public HsBedType selectHsBedTypeById(Long id)
    {
        return hsBedTypeMapper.selectHsBedTypeById(id);
    }

    /**
     * 查询民宿床类型列表
     * 
     * @param hsBedType 民宿床类型
     * @return 民宿床类型
     */
    @Override
    public List<HsBedType> selectHsBedTypeList(HsBedType hsBedType)
    {
        return hsBedTypeMapper.selectHsBedTypeList(hsBedType);
    }

    /**
     * 新增民宿床类型
     * 
     * @param hsBedType 民宿床类型
     * @return 结果
     */
    @Override
    public int insertHsBedType(HsBedType hsBedType)
    {
        return hsBedTypeMapper.insertHsBedType(hsBedType);
    }

    /**
     * 修改民宿床类型
     * 
     * @param hsBedType 民宿床类型
     * @return 结果
     */
    @Override
    public int updateHsBedType(HsBedType hsBedType)
    {
        return hsBedTypeMapper.updateHsBedType(hsBedType);
    }

    /**
     * 批量删除民宿床类型
     * 
     * @param ids 需要删除的民宿床类型主键
     * @return 结果
     */
    @Override
    public int deleteHsBedTypeByIds(Long[] ids)
    {
        return hsBedTypeMapper.deleteHsBedTypeByIds(ids);
    }

    /**
     * 删除民宿床类型信息
     * 
     * @param id 民宿床类型主键
     * @return 结果
     */
    @Override
    public int deleteHsBedTypeById(Long id)
    {
        return hsBedTypeMapper.deleteHsBedTypeById(id);
    }
}
