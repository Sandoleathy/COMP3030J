package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsGuest;

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
}
