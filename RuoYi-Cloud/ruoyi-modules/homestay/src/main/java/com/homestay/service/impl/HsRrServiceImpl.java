package com.homestay.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.homestay.domain.HsReservation;
import com.homestay.domain.HsRoom;
import com.homestay.dto.HsRrDTO;
import com.homestay.mapper.HsReservationMapper;
import com.homestay.mapper.HsRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsRrMapper;
import com.homestay.domain.HsRr;
import com.homestay.service.IHsRrService;

/**
 * 订单客户联合Service业务层处理
 *
 * @author paru
 * @date 2024-04-10
 */
@Service
public class HsRrServiceImpl implements IHsRrService {
    @Autowired
    private HsRrMapper hsRrMapper;

    @Autowired
    private HsReservationMapper hsReservationMapper;

    @Autowired
    private HsRoomMapper hsRoomMapper;

    /**
     * 查询订单客户联合
     *
     * @param id 订单客户联合主键
     * @return 订单客户联合
     */
    @Override
    public HsRrDTO selectHsRrById(Long id) {
        HsRr hsRr=hsRrMapper.selectHsRrById(id);
        return new HsRrDTO(hsReservationMapper.selectHsReservationById(hsRr.getReservationId()), hsRoomMapper.selectHsRoomById(hsRr.getRoomId()));
    }

    /**
     * 查询订单客户联合列表
     *
     * @param hsRr 订单客户联合
     * @return 订单客户联合
     */
    @Override
    public List<HsRrDTO> selectHsRrList(HsRr hsRr) {
        List<HsRr> hsRrList = hsRrMapper.selectHsRrList(hsRr);
        List<HsRrDTO> hsRrDTOS = new ArrayList<>();
        for (HsRr rr : hsRrList) {
            hsRrDTOS.add(new HsRrDTO(hsReservationMapper.selectHsReservationById(rr.getReservationId()), hsRoomMapper.selectHsRoomById(rr.getRoomId())));
        }
        return hsRrDTOS;
    }

    /**
     * 新增订单客户联合
     *
     * @param hsRr 订单客户联合
     * @return 结果
     */
    @Override
    public int insertHsRr(HsRr hsRr) {
        return hsRrMapper.insertHsRr(hsRr);
    }

    /**
     * 修改订单客户联合
     *
     * @param hsRr 订单客户联合
     * @return 结果
     */
    @Override
    public int updateHsRr(HsRr hsRr) {
        return hsRrMapper.updateHsRr(hsRr);
    }

    /**
     * 批量删除订单客户联合
     *
     * @param ids 需要删除的订单客户联合主键
     * @return 结果
     */
    @Override
    public int deleteHsRrByIds(Long[] ids) {
        return hsRrMapper.deleteHsRrByIds(ids);
    }

    /**
     * 删除订单客户联合信息
     *
     * @param id 订单客户联合主键
     * @return 结果
     */
    @Override
    public int deleteHsRrById(Long id) {
        return hsRrMapper.deleteHsRrById(id);
    }

    /**
     * 根据房间ID查询订单客户联合列表
     *
     * @param roomId 房间ID
     * @return 订单客户联合集合
     */
    @Override
    public List<HsReservation> selectHsRrByRoomId(Long roomId) {
        List<HsReservation> reservations = new ArrayList<>();
        List<HsRr> hsRrList = hsRrMapper.selectHsRrByRoomId(roomId);
        for (HsRr hsRr : hsRrList) {
            reservations.add(hsReservationMapper.selectHsReservationById(hsRr.getReservationId()));
        }
        return reservations;
    }

    /**
     * 根据预订ID查询订单房间联合列表
     *
     * @param reservationId 预订ID
     * @return 订单房间联合集合
     */
    @Override
    public List<HsRoom> selectHsRrByReservationId(Long reservationId) {
        List<HsRoom> rooms = new ArrayList<>();
        List<HsRr> hsRrList = hsRrMapper.selectHsRrByReservationId(reservationId);
        for (HsRr hsRr : hsRrList) {
            rooms.add(hsRoomMapper.selectHsRoomById(hsRr.getRoomId()));
        }
        return rooms;
    }
}
