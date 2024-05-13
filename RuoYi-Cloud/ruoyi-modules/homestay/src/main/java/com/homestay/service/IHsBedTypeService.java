package com.homestay.service;

import java.util.List;
import com.homestay.domain.HsBedType;
import com.homestay.dto.AddBedTypeDTO;
import com.homestay.dto.SelectBedTypeDTO;

/**
 * 民宿床类型Service接口
 * 
 * @author paru
 * @date 2024-04-10
 */
public interface IHsBedTypeService 
{
    /**
     * 查询民宿床类型
     * 
     * @param id 民宿床类型主键
     * @return 民宿床类型
     */
    public AddBedTypeDTO selectHsBedTypeById(Long id);

    /**
     * 查询民宿床类型列表
     * 
     * @param selectBedTypeDTO 民宿床类型DTO
     * @return 民宿床类型集合
     */
    public List<AddBedTypeDTO> selectHsBedTypeList(SelectBedTypeDTO selectBedTypeDTO);

    /**
     * 新增民宿床类型
     * 
     * @param addBedTypeDTO 民宿床类型DTO
     * @return 结果
     */
    public int insertHsBedType(AddBedTypeDTO addBedTypeDTO);

    /**
     * 修改民宿床类型
     * 
     * @param hsBedType 民宿床类型
     * @return 结果
     */
    public int updateHsBedType(HsBedType hsBedType);

    /**
     * 批量删除民宿床类型
     * 
     * @param ids 需要删除的民宿床类型主键集合
     * @return 结果
     */
    public int[] deleteHsBedTypeByIds(Long[] ids);

    /**
     * 删除民宿床类型信息
     * 
     * @param id 民宿床类型主键
     * @return 结果
     */
    public int deleteHsBedTypeById(Long id);
}
