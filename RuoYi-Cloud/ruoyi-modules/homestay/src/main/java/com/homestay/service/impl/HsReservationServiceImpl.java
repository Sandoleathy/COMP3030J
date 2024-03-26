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
 * 房间预定Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-26
 */
@Service
public class HsReservationServiceImpl implements IHsReservationService 
{
    @Autowired
    private HsReservationMapper hsReservationMapper;

    /**
     * 查询房间预定
     * 
     * @param id 房间预定主键
     * @return 房间预定
     */
    @Override
    public HsReservation selectHsReservationById(Long id)
    {
        return hsReservationMapper.selectHsReservationById(id);
    }

    /**
     * 查询房间预定列表
     * 
     * @param hsReservation 房间预定
     * @return 房间预定
     */
    @Override
    public List<HsReservation> selectHsReservationList(HsReservation hsReservation)
    {
        return hsReservationMapper.selectHsReservationList(hsReservation);
    }

    /**
     * 新增房间预定
     * 
     * @param hsReservation 房间预定
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
     * 修改房间预定
     * 
     * @param hsReservation 房间预定
     * @return 结果
     */
    @Transactional
    @Override
    public int updateHsReservation(HsReservation hsReservation)
    {
        hsReservationMapper.deleteHsRoomByRoomNumber(hsReservation.getId());
        insertHsRoom(hsReservation);
        return hsReservationMapper.updateHsReservation(hsReservation);
    }

    /**
     * 批量删除房间预定
     * 
     * @param ids 需要删除的房间预定主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHsReservationByIds(Long[] ids)
    {
        hsReservationMapper.deleteHsRoomByRoomNumbers(ids);
        return hsReservationMapper.deleteHsReservationByIds(ids);
    }

    /**
     * 删除房间预定信息
     * 
     * @param id 房间预定主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHsReservationById(Long id)
    {
        hsReservationMapper.deleteHsRoomByRoomNumber(id);
        return hsReservationMapper.deleteHsReservationById(id);
    }

    /**
     * 新增民宿房间信息
     * 
     * @param hsReservation 房间预定对象
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
                hsRoom.setRoomNumber(id);
                list.add(hsRoom);
            }
            if (list.size() > 0)
            {
                hsReservationMapper.batchHsRoom(list);
            }
        }
    }
}
