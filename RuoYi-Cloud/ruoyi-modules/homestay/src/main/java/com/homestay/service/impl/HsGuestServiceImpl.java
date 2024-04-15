package com.homestay.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.homestay.domain.HsReservation;
import com.homestay.domain.HsRoom;
import com.homestay.mapper.HsReservationMapper;
import com.homestay.mapper.HsRoomMapper;
import com.ruoyi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsGuestMapper;
import com.homestay.domain.HsGuest;
import com.homestay.service.IHsGuestService;

/**
 * 民宿客户表Service业务层处理
 * 
 * @author paru
 * @date 2024-04-09
 */
@Service
public class HsGuestServiceImpl implements IHsGuestService 
{
    @Autowired
    private HsGuestMapper hsGuestMapper;

    @Autowired
    private HsReservationMapper hsReservationMapper;

    /**
     * 查询民宿客户表
     * 
     * @param id 民宿客户表主键
     * @return 民宿客户表
     */
    @Override
    public HsGuest selectHsGuestById(Long id)
    {
        return hsGuestMapper.selectHsGuestById(id);
    }

    /**
     * 查询民宿客户表列表
     * 
     * @param hsGuest 民宿客户表
     * @return 民宿客户表
     */
    @Override
    public List<HsGuest> selectHsGuestList(HsGuest hsGuest)
    {
        return hsGuestMapper.selectHsGuestList(hsGuest);
    }

    /**
     * 新增民宿客户表
     * 
     * @param hsGuest 民宿客户表
     * @return 结果
     */
    @Override
    public int insertHsGuest(HsGuest hsGuest)
    {
        return hsGuestMapper.insertHsGuest(hsGuest);
    }

    /**
     * 修改民宿客户表
     * 
     * @param hsGuest 民宿客户表
     * @return 结果
     */
    @Override
    public int updateHsGuest(HsGuest hsGuest)
    {
        return hsGuestMapper.updateHsGuest(hsGuest);
    }

    /**
     * 批量删除民宿客户表
     * 
     * @param ids 需要删除的民宿客户表主键
     * @return 结果
     */
    @Override
    public int deleteHsGuestByIds(Long[] ids)
    {
        return hsGuestMapper.deleteHsGuestByIds(ids);
    }

    /**
     * 删除民宿客户表信息
     * 
     * @param id 民宿客户表主键
     * @return 结果
     */
    @Override
    public int deleteHsGuestById(Long id)
    {
        return hsGuestMapper.deleteHsGuestById(id);
    }

    @Override
    public List<HsGuest> selectHsGuestsByGuestId(Long guestId) {
        return hsGuestMapper.selectHsGuestsByGuestId(guestId);
    }

    /**
     * 根据预订ID查询所有相关的客户信息
     *
     * @param reservationId 预订ID
     * @return 客户信息列表
     */
    @Override
    public List<HsGuest> selectHsGuestsByReservationId(Long reservationId) {
        return hsGuestMapper.selectHsGuestsByReservationId(reservationId);
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return SysUser 用户信息对象
     */
    @Override
    public SysUser selectSysUserById(Long userId) {
        return hsGuestMapper.selectSysUserById(userId);
    }

    @Override
    public List<SysUser> selectSysUsersByReservationId(Long reservationId){
        List<SysUser> results=new ArrayList<>();
        List<HsGuest> guests=hsGuestMapper.selectHsGuestsByReservationId(reservationId);
        for (HsGuest hsGuest:guests){
            results.add(hsGuestMapper.selectSysUserById(hsGuest.getGuestId()));
        }
        return results;
    }

    public List<HsReservation> selectReservationByUserId(Long userId){
        List<HsReservation> results=new ArrayList<>();
        List<HsGuest> guests=hsGuestMapper.selectHsGuestsByGuestId(userId);
        for (HsGuest hsGuest:guests){
            results.add(hsReservationMapper.selectHsReservationById(hsGuest.getReservationId()));
        }

        return results;
    }
}
