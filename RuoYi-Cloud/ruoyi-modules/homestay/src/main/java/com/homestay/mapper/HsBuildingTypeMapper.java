package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsBuildingType;

/**
 * 民宿栋类型Mapper接口
 * 
 * @author paru
 * @date 2024-04-09
 */
public interface HsBuildingTypeMapper 
{
    /**
     * 查询民宿栋类型
     * 
     * @param id 民宿栋类型主键
     * @return 民宿栋类型
     */
    public HsBuildingType selectHsBuildingTypeById(Long id);

    /**
     * 查询民宿栋类型列表
     * 
     * @param hsBuildingType 民宿栋类型
     * @return 民宿栋类型集合
     */
    public List<HsBuildingType> selectHsBuildingTypeList(HsBuildingType hsBuildingType);

    /**
     * 新增民宿栋类型
     * 
     * @param hsBuildingType 民宿栋类型
     * @return 结果
     */
    public int insertHsBuildingType(HsBuildingType hsBuildingType);

    /**
     * 修改民宿栋类型
     * 
     * @param hsBuildingType 民宿栋类型
     * @return 结果
     */
    public int updateHsBuildingType(HsBuildingType hsBuildingType);

    /**
     * 删除民宿栋类型
     * 
     * @param id 民宿栋类型主键
     * @return 结果
     */
    public int deleteHsBuildingTypeById(Long id);

    /**
     * 批量删除民宿栋类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsBuildingTypeByIds(Long[] ids);
}
