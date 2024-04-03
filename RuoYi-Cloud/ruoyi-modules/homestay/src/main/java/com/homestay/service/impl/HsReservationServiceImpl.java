package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.core.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.homestay.domain.HsRoom;
import com.homestay.mapper.HsReservationMapper;
import com.homestay.domain.HsReservation;
import com.homestay.service.IHsReservationService;

/**
 * 民宿预订Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-03
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
    @Transactional
    @Override
    public int insertHsReservation(HsReservation hsReservation)
    {
        int rows = hsReservationMapper.insertHsReservation(hsReservation);
        insertHsRoom(hsReservation);
        return rows;
    }

    /**
     * 修改民宿预订
     * 
     * @param hsReservation 民宿预订
     * @return 结果
     */
    @Transactional
    @Override
    public int updateHsReservation(HsReservation hsReservation)
    {
        hsReservationMapper.deleteHsRoomById(hsReservation.getId());
        insertHsRoom(hsReservation);
        return hsReservationMapper.updateHsReservation(hsReservation);
    }

    /**
     * 批量删除民宿预订
     * 
     * @param ids 需要删除的民宿预订主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHsReservationByIds(Long[] ids)
    {
        hsReservationMapper.deleteHsRoomByIds(ids);
        return hsReservationMapper.deleteHsReservationByIds(ids);
    }

    /**
     * 删除民宿预订信息
     * 
     * @param id 民宿预订主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHsReservationById(Long id)
    {
        hsReservationMapper.deleteHsRoomById(id);
        return hsReservationMapper.deleteHsReservationById(id);
    }

    /**
     * 新增民宿房间信息
     * 
     * @param hsReservation 民宿预订对象
     */
    public void insertHsRoom(HsReservation hsReservation)
    {
        List<HsRoom> hsRoomList = hsReservation.getHsRoomList();
        Long id = hsReservation.getId();
        if (StringUtils.isNotNull(hsRoomList))
        {
            List<HsRoom> list = new ArrayList<HsRoom>();
            for (HsRoom hsRoom : hsRoomList)
            {
                hsRoom.setId(id);
                list.add(hsRoom);
            }
            if (list.size() > 0)
            {
                hsReservationMapper.batchHsRoom(list);
            }
        }
    }
}
