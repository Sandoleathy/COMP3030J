package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsReservationMapper;
import com.homestay.domain.HsReservation;
import com.homestay.service.IHsReservationService;

/**
 * 民宿预订Service业务层处理
 * 
 * @author paru
 * @date 2024-04-09
 */
@Service
public class HsReservationServiceImpl implements IHsReservationService 
{
    @Autowired
    private HsReservationMapper hsReservationMapper;

    /**
     * 查询民宿预订
     * 
     * @param id 民宿预订主键
     * @return 民宿预订
     */
    @Override
    public HsReservation selectHsReservationById(Long id)
    {
        return hsReservationMapper.selectHsReservationById(id);
    }

    /**
     * 查询民宿预订列表
     * 
     * @param hsReservation 民宿预订
     * @return 民宿预订
     */
    @Override
    public List<HsReservation> selectHsReservationList(HsReservation hsReservation)
    {
        return hsReservationMapper.selectHsReservationList(hsReservation);
    }

    /**
     * 新增民宿预订
     * 
     * @param hsReservation 民宿预订
     * @return 结果
     */
    @Override
    public int insertHsReservation(HsReservation hsReservation)
    {
        return hsReservationMapper.insertHsReservation(hsReservation);
    }

    /**
     * 修改民宿预订
     * 
     * @param hsReservation 民宿预订
     * @return 结果
     */
    @Override
    public int updateHsReservation(HsReservation hsReservation)
    {
        return hsReservationMapper.updateHsReservation(hsReservation);
    }

    /**
     * 批量删除民宿预订
     * 
     * @param ids 需要删除的民宿预订主键
     * @return 结果
     */
    @Override
    public int deleteHsReservationByIds(Long[] ids)
    {
        return hsReservationMapper.deleteHsReservationByIds(ids);
    }

    /**
     * 删除民宿预订信息
     * 
     * @param id 民宿预订主键
     * @return 结果
     */
    @Override
    public int deleteHsReservationById(Long id)
    {
        return hsReservationMapper.deleteHsReservationById(id);
    }
}
