package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsGuest;
import com.ruoyi.system.api.domain.SysUser;

/**
 * 民宿客户表Mapper接口
 * 
 * @author paru
 * @date 2024-04-09
 */
public interface HsGuestMapper 
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
     * 删除民宿客户表
     * 
     * @param id 民宿客户表主键
     * @return 结果
     */
    public int deleteHsGuestById(Long id);

    /**
     * 批量删除民宿客户表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsGuestByIds(Long[] ids);

    /**
     * 查询民宿客户表通过用户ID
     *
     * @param guestId 用户表的ID
     * @return 民宿客户表列表
     */
    List<HsGuest> selectHsGuestsByGuestId(Long guestId);

    /**
     * 根据预订ID查询民宿客户表列表
     *
     * @param reservationId 预订ID
     * @return 民宿客户表列表
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
     * 根据用户对象的属性搜索匹配的用户
     *
     * @param sysUser 用户对象，包含搜索条件
     * @return 匹配的用户列表
     */
    List<SysUser> selectSysUserByUser(SysUser sysUser);

    int insertSysUser(SysUser sysUser);
}
