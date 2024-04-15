package com.homestay.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.homestay.domain.HsGuest;
import com.homestay.domain.HsReservation;
import com.homestay.dto.ConsumptionDTO;
import com.homestay.dto.SelectConsumptionDTO;
import com.homestay.mapper.HsGuestMapper;
import com.homestay.mapper.HsReservationMapper;
import com.ruoyi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsConsumptionMapper;
import com.homestay.domain.HsConsumption;
import com.homestay.service.IHsConsumptionService;

/**
 * 民宿消费Service业务层处理
 *
 * @author paru
 * @date 2024-04-13
 */
@Service
public class HsConsumptionServiceImpl implements IHsConsumptionService {
    @Autowired
    private HsConsumptionMapper hsConsumptionMapper;

    @Autowired
    private HsReservationMapper hsReservationMapper;

    @Autowired
    private HsGuestMapper hsGuestMapper;

    /**
     * 查询民宿消费
     *
     * @param id 民宿消费主键
     * @return 民宿消费
     */
    @Override
    public HsConsumption selectHsConsumptionById(Long id) {
        return hsConsumptionMapper.selectHsConsumptionById(id);
    }

    /**
     * 查询民宿消费列表
     *
     * @param selectConsumptionDTO 民宿消费DTO
     * @return 民宿消费
     */
    @Override
    public List<ConsumptionDTO> selectHsConsumptionList(SelectConsumptionDTO selectConsumptionDTO) {
        HsReservation hsReservation = new HsReservation();
        hsReservation.setContactInformation(selectConsumptionDTO.getContactInformation());
        hsReservation.setCheckinTime(selectConsumptionDTO.getCheckinTime());
        hsReservation.setCheckoutTime(selectConsumptionDTO.getCheckoutTime());
        hsReservation.setNumberOfGuests(selectConsumptionDTO.getNumberOfGuests());
        hsReservation.setNumberOfRooms(selectConsumptionDTO.getNumberOfRooms());
        hsReservation.setReservationTime(selectConsumptionDTO.getReservationTime());
        hsReservation.setRequests(selectConsumptionDTO.getRequests());
        hsReservation.setTotalPrice(selectConsumptionDTO.getTotalPrice());
        hsReservation.setPay(selectConsumptionDTO.getPay());
        hsReservation.setReservationStatus(selectConsumptionDTO.getReservationStatus());

        SysUser user = new SysUser();
        user.setUserName(selectConsumptionDTO.getUserName());
        user.setEmail(selectConsumptionDTO.getEmail());
        user.setPhonenumber(selectConsumptionDTO.getPhoneNumber());

        HsConsumption consumption = new HsConsumption();
        consumption.setDatail(selectConsumptionDTO.getDatail());
        consumption.setPrice(selectConsumptionDTO.getPrice());
        consumption.setTime(selectConsumptionDTO.getTime());
        consumption.setRid(selectConsumptionDTO.getRid());
        consumption.setUid(selectConsumptionDTO.getUid());

        List<HsReservation> reservations = hsReservationMapper.selectHsReservationList(hsReservation);
        List<SysUser> users = hsGuestMapper.selectSysUserByUser(user);
        List<HsConsumption> consumptionList = hsConsumptionMapper.selectHsConsumptionList(consumption);

        List<ConsumptionDTO> results = new ArrayList<>();

        if (reservations.size() != 0 && users.size() != 0) {
            for (HsReservation reservation : reservations) {
                for (SysUser sysUser : users) {
                    HsGuest guest = new HsGuest();
                    guest.setGuestId(sysUser.getUserId());
                    guest.setReservationId(reservation.getId());
                    if (hsGuestMapper.selectHsGuestList(guest).size() != 0) {
                        List<HsConsumption> consumptions = new ArrayList<>();
                        for (HsConsumption hsConsumption : consumptionList) {
                            if (hsConsumption.getRid().equals(reservation.getId()) && hsConsumption.getUid().equals(sysUser.getUserId())) {
                                consumptions.add(hsConsumption);
                            }
                        }
                        if (consumptions.size()!=0){
                            results.add(new ConsumptionDTO(reservation, sysUser.getUserName(), sysUser.getEmail(), sysUser.getPhonenumber(), consumptions));
                        }
                    }

                }
            }
        }

        return results;
    }

    /**
     * 新增民宿消费
     *
     * @param hsConsumption 民宿消费
     * @return 结果
     */
    @Override
    public int insertHsConsumption(HsConsumption hsConsumption) {
        int result = hsConsumptionMapper.insertHsConsumption(hsConsumption);
        if (result > 0) {
            HsReservation reservation = hsReservationMapper.selectHsReservationById(hsConsumption.getRid());
            reservation.setTotalPrice(reservation.getTotalPrice() + hsConsumption.getPrice());
            hsReservationMapper.updateHsReservation(reservation);
        }
        return result;
    }

    /**
     * 修改民宿消费
     *
     * @param hsConsumption 民宿消费
     * @return 结果
     */
    @Override
    public int updateHsConsumption(HsConsumption hsConsumption) {
        HsConsumption consumption = hsConsumptionMapper.selectHsConsumptionById(hsConsumption.getId());
        HsReservation reservation = hsReservationMapper.selectHsReservationById(hsConsumption.getRid());
        reservation.setTotalPrice(reservation.getTotalPrice() + hsConsumption.getPrice() - consumption.getPrice());
        hsReservationMapper.updateHsReservation(reservation);
        return hsConsumptionMapper.updateHsConsumption(hsConsumption);
    }

    /**
     * 批量删除民宿消费
     *
     * @param ids 需要删除的民宿消费主键
     * @return 结果
     */
    @Override
    public int deleteHsConsumptionByIds(Long[] ids) {
        return hsConsumptionMapper.deleteHsConsumptionByIds(ids);
    }

    /**
     * 删除民宿消费信息
     *
     * @param id 民宿消费主键
     * @return 结果
     */
    @Override
    public int deleteHsConsumptionById(Long id) {
        return hsConsumptionMapper.deleteHsConsumptionById(id);
    }
}
