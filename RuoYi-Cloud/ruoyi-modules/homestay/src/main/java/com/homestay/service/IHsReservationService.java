package com.homestay.service;

import java.util.List;
import com.homestay.domain.HsReservation;

/**
 * 房间预定Service接口
 * 
 * @author ruoyi
 * @date 2024-03-26
 */
public interface IHsReservationService 
{
    /**
     * 查询房间预定
     * 
     * @param id 房间预定主键
     * @return 房间预定
     */
    public HsReservation selectHsReservationById(Long id);

    /**
     * 查询房间预定列表
     * 
     * @param hsReservation 房间预定
     * @return 房间预定集合
     */
    public List<HsReservation> selectHsReservationList(HsReservation hsReservation);

    /**
     * 新增房间预定
     * 
     * @param hsReservation 房间预定
     * @return 结果
     */
    public int insertHsReservation(HsReservation hsReservation);

    /**
     * 修改房间预定
     * 
     * @param hsReservation 房间预定
     * @return 结果
     */
    public int updateHsReservation(HsReservation hsReservation);

    /**
     * 批量删除房间预定
     * 
     * @param ids 需要删除的房间预定主键集合
     * @return 结果
     */
    public int deleteHsReservationByIds(Long[] ids);

    /**
     * 删除房间预定信息
     * 
     * @param id 房间预定主键
     * @return 结果
     */
    public int deleteHsReservationById(Long id);
}
