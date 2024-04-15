package com.homestay.service;

import java.util.List;
import com.homestay.domain.HsGuest;
import com.homestay.domain.HsReservation;
import com.ruoyi.system.api.domain.SysUser;

/**
 * 民宿客户表Service接口
 * 
 * @author paru
 * @date 2024-04-09
 */
public interface IHsGuestService 
{
    /**
     * 查询民宿客户表
     * 
     * @param id 民宿客户表主键
     * @return 民宿客户表
     */
    public HsGuest selectHsGuestById(Long id);

    /**
     * 查询民宿客户表列表
     * 
     * @param hsGuest 民宿客户表
     * @return 民宿客户表集合
     */
    public List<HsGuest> selectHsGuestList(HsGuest hsGuest);

    /**
     * 新增民宿客户表
     * 
     * @param hsGuest 民宿客户表
     * @return 结果
     */
    public int insertHsGuest(HsGuest hsGuest);

    /**
     * 修改民宿客户表
     * 
     * @param hsGuest 民宿客户表
     * @return 结果
     */
    public int updateHsGuest(HsGuest hsGuest);

    /**
     * 批量删除民宿客户表
     * 
     * @param ids 需要删除的民宿客户表主键集合
     * @return 结果
     */
    public int deleteHsGuestByIds(Long[] ids);

    /**
     * 删除民宿客户表信息
     * 
     * @param id 民宿客户表主键
     * @return 结果
     */
    public int deleteHsGuestById(Long id);

    /**
     * 查询民宿客户表通过用户ID
     *
     * @param guestId 用户表的ID
     * @return 民宿客户表列表
     */
    List<HsGuest> selectHsGuestsByGuestId(Long guestId);

    /**
     * 根据预订ID查询所有相关的客户信息
     *
     * @param reservationId 预订ID
     * @return 客户信息列表
     */
    List<HsGuest> selectHsGuestsByReservationId(Long reservationId);

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return SysUser 用户信息对象
     */
    SysUser selectSysUserById(Long userId);

    /**
     * 根据预订ID查询民宿客户表列表
     *
     * @param reservationId 预订ID
     * @return 民宿客户表列表
     */
    List<SysUser> selectSysUsersByReservationId(Long reservationId);

    /**
     * 根据用户ID查询预订信息
     *
     * @param userId 用户ID
     * @return SysUser 用户信息对象
     */
    List<HsReservation> selectReservationByUserId(Long userId);
}
