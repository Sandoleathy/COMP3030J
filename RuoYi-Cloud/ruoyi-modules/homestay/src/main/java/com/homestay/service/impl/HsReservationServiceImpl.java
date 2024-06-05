package com.homestay.service.impl;

import com.homestay.domain.*;
import com.homestay.dto.ReservationDTO;
import com.homestay.mapper.*;
import com.homestay.service.IHsReservationService;
import com.ruoyi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    private HsFinanceMapper hsFinanceMapper;

    @Autowired
    private HsRoomMapper hsRoomMapper;

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
    public List<ReservationDTO> select(HsReservation hsReservation) {
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        List<HsReservation> reservations = hsReservationMapper.selectHsReservationList(hsReservation);
        List<HsRoom> rooms = new ArrayList<>();
        List<SysUser> sysUsers = new ArrayList<>();
        for (HsReservation reservation : reservations) {
            List<HsRr> hsRrList = hsRrMapper.selectHsRrByReservationId(reservation.getId());
            for (HsRr hsRr : hsRrList) {
                rooms.add(hsRoomMapper.selectHsRoomById(hsRr.getRoomId()));
            }
            List<HsGuest> hsGuestList = hsGuestMapper.selectHsGuestsByReservationId(reservation.getId());
            for (HsGuest hsGuest : hsGuestList) {
                sysUsers.add(hsGuestMapper.selectSysUserById(hsGuest.getGuestId()));
            }
            reservationDTOList.add(new ReservationDTO(reservation, rooms, sysUsers));
        }
        return reservationDTOList;
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

    @Override
    public int updateReservationStatusToPaid(Long id, String description) {
        HsReservation hsReservation = hsReservationMapper.selectHsReservationById(id);
        if (hsReservation != null) {
            HsFinance hsFinance = new HsFinance();
            hsFinance.setTime(hsReservation.getReservationTime());
            hsFinance.setType("income");
            hsFinance.setNum(new BigDecimal(hsReservation.getTotalPrice()));
            hsFinance.setDescription(description);
            hsFinanceMapper.insertHsFinance(hsFinance);
        }
        return hsReservationMapper.updateReservationStatusToPaid(id);
    }

}
