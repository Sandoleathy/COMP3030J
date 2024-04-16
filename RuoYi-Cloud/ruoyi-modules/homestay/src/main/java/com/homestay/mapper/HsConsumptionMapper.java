package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsConsumption;

/**
 * 民宿消费Mapper接口
 * 
 * @author paru
 * @date 2024-04-13
 */
public interface HsConsumptionMapper 
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
     * @param hsConsumption 民宿消费
     * @return 民宿消费集合
     */
    public List<HsConsumption> selectHsConsumptionList(HsConsumption hsConsumption);

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
     * 删除民宿消费
     * 
     * @param id 民宿消费主键
     * @return 结果
     */
    public int deleteHsConsumptionById(Long id);

    /**
     * 批量删除民宿消费
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsConsumptionByIds(Long[] ids);

    /**
     * 根据预订ID查询消费记录
     *
     * @param rid 预订ID
     * @return 消费记录列表
     */
    List<HsConsumption> selectHsConsumptionByRid(Long rid);

    /**
     * 根据用户ID查询消费记录
     *
     * @param uid 用户ID
     * @return 消费记录列表
     */
    List<HsConsumption> selectHsConsumptionByUid(Long uid);

    /**
     * 根据预订ID和用户ID查询消费记录
     *
     * @param rid 预订ID
     * @param uid 用户ID
     * @return 消费记录列表
     */
    List<HsConsumption> selectHsConsumptionByRidAndUid(Long rid, Long uid);
}