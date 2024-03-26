package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsReservation;
import com.homestay.domain.HsRoom;

/**
 * 房间预定Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-26
 */
public interface HsReservationMapper 
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
     * 删除房间预定
     * 
     * @param id 房间预定主键
     * @return 结果
     */
    public int deleteHsReservationById(Long id);

    /**
     * 批量删除房间预定
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsReservationByIds(Long[] ids);

    /**
     * 批量删除民宿房间
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsRoomByRoomNumbers(Long[] ids);
    
    /**
     * 批量新增民宿房间
     * 
     * @param hsRoomList 民宿房间列表
     * @return 结果
     */
    public int batchHsRoom(List<HsRoom> hsRoomList);
    

    /**
     * 通过房间预定主键删除民宿房间信息
     * 
     * @param id 房间预定ID
     * @return 结果
     */
    public int deleteHsRoomByRoomNumber(Long id);
}
