package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsConsumptionMapper;
import com.homestay.domain.HsConsumption;
import com.homestay.service.IHsConsumptionService;

/**
 * 民宿消费Service业务层处理
 * 
 * @author paru
 * @date 2024-04-09
 */
@Service
public class HsConsumptionServiceImpl implements IHsConsumptionService 
{
    @Autowired
    private HsConsumptionMapper hsConsumptionMapper;

    /**
     * 查询民宿消费
     * 
     * @param id 民宿消费主键
     * @return 民宿消费
     */
    @Override
    public HsConsumption selectHsConsumptionById(Long id)
    {
        return hsConsumptionMapper.selectHsConsumptionById(id);
    }

    /**
     * 查询民宿消费列表
     * 
     * @param hsConsumption 民宿消费
     * @return 民宿消费
     */
    @Override
    public List<HsConsumption> selectHsConsumptionList(HsConsumption hsConsumption)
    {
        return hsConsumptionMapper.selectHsConsumptionList(hsConsumption);
    }

    /**
     * 新增民宿消费
     * 
     * @param hsConsumption 民宿消费
     * @return 结果
     */
    @Override
    public int insertHsConsumption(HsConsumption hsConsumption)
    {
        return hsConsumptionMapper.insertHsConsumption(hsConsumption);
    }

    /**
     * 修改民宿消费
     * 
     * @param hsConsumption 民宿消费
     * @return 结果
     */
    @Override
    public int updateHsConsumption(HsConsumption hsConsumption)
    {
        return hsConsumptionMapper.updateHsConsumption(hsConsumption);
    }

    /**
     * 批量删除民宿消费
     * 
     * @param ids 需要删除的民宿消费主键
     * @return 结果
     */
    @Override
    public int deleteHsConsumptionByIds(Long[] ids)
    {
        return hsConsumptionMapper.deleteHsConsumptionByIds(ids);
    }

    /**
     * 删除民宿消费信息
     * 
     * @param id 民宿消费主键
     * @return 结果
     */
    @Override
    public int deleteHsConsumptionById(Long id)
    {
        return hsConsumptionMapper.deleteHsConsumptionById(id);
    }
}
