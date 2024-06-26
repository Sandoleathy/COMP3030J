package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsRr;

/**
 * 订单客户联合Mapper接口
 * 
 * @author paru
 * @date 2024-04-10
 */
public interface HsRrMapper 
{
    /**
     * 查询订单客户联合
     * 
     * @param id 订单客户联合主键
     * @return 订单客户联合
     */
    public HsRr selectHsRrById(Long id);

    /**
     * 查询订单客户联合列表
     * 
     * @param hsRr 订单客户联合
     * @return 订单客户联合集合
     */
    public List<HsRr> selectHsRrList(HsRr hsRr);

    /**
     * 新增订单客户联合
     * 
     * @param hsRr 订单客户联合
     * @return 结果
     */
    public int insertHsRr(HsRr hsRr);

    /**
     * 修改订单客户联合
     * 
     * @param hsRr 订单客户联合
     * @return 结果
     */
    public int updateHsRr(HsRr hsRr);

    /**
     * 删除订单客户联合
     * 
     * @param id 订单客户联合主键
     * @return 结果
     */
    public int deleteHsRrById(Long id);

    /**
     * 批量删除订单客户联合
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsRrByIds(Long[] ids);

    /**
     * 查询基于房间ID的订单客户联合列表
     *
     * @param roomId 房间ID
     * @return 订单客户联合集合
     */
    List<HsRr> selectHsRrByRoomId(Long roomId);

    /**
     * 查询基于预订ID的订单房间联合列表
     *
     * @param reservationId 预订ID
     * @return 订单房间联合对象集合
     */
    List<HsRr> selectHsRrByReservationId(Long reservationId);
}
