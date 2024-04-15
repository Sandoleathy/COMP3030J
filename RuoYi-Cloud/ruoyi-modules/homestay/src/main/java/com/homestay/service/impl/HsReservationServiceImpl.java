package com.homestay.service.impl;

import com.homestay.domain.HsGuest;
import com.homestay.domain.HsReservation;
import com.homestay.domain.HsRr;
import com.homestay.mapper.HsGuestMapper;
import com.homestay.mapper.HsReservationMapper;
import com.homestay.mapper.HsRoomMapper;
import com.homestay.mapper.HsRrMapper;
import com.homestay.service.IHsReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 民宿预订Service业务层处理
 *
 * @author paru
 * @date 2024-04-09
 */
@Service
public class HsReservationServiceImpl implements IHsReservationService {
    @Autowired
    private HsReservationMapper hsReservationMapper;

    @Autowired
    private HsGuestMapper hsGuestMapper;

    @Autowired
    private HsRrMapper hsRrMapper;

    /**
     * 查询民宿预订
     *
     * @param id 民宿预订主键
     * @return 民宿预订
     */
    @Override
    public HsReservation selectHsReservationById(Long id) {
        return hsReservationMapper.selectHsReservationById(id);
    }

    /**
     * 查询民宿预订列表
     *
     * @param hsReservation 民宿预订
     * @return 民宿预订
     */
    @Override
    public List<HsReservation> select(HsReservation hsReservation) {
        return hsReservationMapper.selectHsReservationList(hsReservation);
    }

    /**
     * 新增民宿预订
     *
     * @param hsReservation 民宿预订
     * @return 结果
     */
    @Override
    public int insertHsReservation(@RequestBody HsReservation hsReservation) {
        return hsReservationMapper.insertHsReservation(hsReservation);
    }

    /**
     * 修改民宿预订
     *
     * @param hsReservation 民宿预订
     * @return 结果
     */
    @Override
    public int updateHsReservation(HsReservation hsReservation) {
        return hsReservationMapper.updateHsReservation(hsReservation);
    }

    /**
     * 批量删除民宿预订
     *
     * @param ids 需要删除的民宿预订主键
     * @return 结果
     */
    @Override
    public int[] deleteHsReservationByIds(Long[] ids) {
        int[] result = new int[ids.length];
        int i = 0;
        for (Long id : ids) {
            result[i] = deleteHsReservationById(id);
            i++;
        }
        return result;
    }

    /**
     * 删除民宿预订信息
     *
     * @param id 民宿预订主键
     * @return 结果
     */
    @Override
    public int deleteHsReservationById(Long id) {
        List<HsRr> hsRrList = hsRrMapper.selectHsRrByReservationId(id);
        for (HsRr hsRr : hsRrList) {
            hsRrMapper.deleteHsRrById(hsRr.getId());
        }
        List<HsGuest> hsGuestList = hsGuestMapper.selectHsGuestsByReservationId(id);
        for (HsGuest hsGuest : hsGuestList) {
            hsGuestMapper.deleteHsGuestById(hsGuest.getId());
        }
        return hsReservationMapper.deleteHsReservationById(id);
    }
}
