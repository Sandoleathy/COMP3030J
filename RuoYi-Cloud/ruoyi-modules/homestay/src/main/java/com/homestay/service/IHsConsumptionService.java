package com.homestay.service;

import java.util.List;
import com.homestay.domain.HsConsumption;
import com.homestay.dto.ConsumptionDTO;
import com.homestay.dto.SelectConsumptionDTO;

/**
 * 民宿消费Service接口
 * 
 * @author paru
 * @date 2024-04-13
 */
public interface IHsConsumptionService 
{
    /**
     * 查询民宿消费
     * 
     * @param id 民宿消费主键
     * @return 民宿消费
     */
    public HsConsumption selectHsConsumptionById(Long id);

    /**
     * 查询民宿消费列表
     * 
     * @param selectConsumptionDTO 民宿消费DTO
     * @return 民宿消费集合
     */
    public List<ConsumptionDTO> selectHsConsumptionList(SelectConsumptionDTO selectConsumptionDTO);

    /**
     * 新增民宿消费
     * 
     * @param hsConsumption 民宿消费
     * @return 结果
     */
    public int insertHsConsumption(HsConsumption hsConsumption);

    /**
     * 修改民宿消费
     * 
     * @param hsConsumption 民宿消费
     * @return 结果
     */
    public int updateHsConsumption(HsConsumption hsConsumption);

    /**
     * 批量删除民宿消费
     * 
     * @param ids 需要删除的民宿消费主键集合
     * @return 结果
     */
    public int deleteHsConsumptionByIds(Long[] ids);

    /**
     * 删除民宿消费信息
     * 
     * @param id 民宿消费主键
     * @return 结果
     */
    public int deleteHsConsumptionById(Long id);
}
