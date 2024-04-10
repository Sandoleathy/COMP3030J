package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsReservation;

/**
 * 民宿预订Mapper接口
 * 
 * @author paru
 * @date 2024-04-09
 */
public interface HsReservationMapper 
{
    /**
     * 查询民宿预订
     * 
     * @param id 民宿预订主键
     * @return 民宿预订
     */
    public HsReservation selectHsReservationById(Long id);

    /**
     * 查询民宿预订列表
     * 
     * @param hsReservation 民宿预订
     * @return 民宿预订集合
     */
    public List<HsReservation> selectHsReservationList(HsReservation hsReservation);

    /**
     * 新增民宿预订
     * 
     * @param hsReservation 民宿预订
     * @return 结果
     */
    public int insertHsReservation(HsReservation hsReservation);

    /**
     * 修改民宿预订
     * 
     * @param hsReservation 民宿预订
     * @return 结果
     */
    public int updateHsReservation(HsReservation hsReservation);

    /**
     * 删除民宿预订
     * 
     * @param id 民宿预订主键
     * @return 结果
     */
    public int deleteHsReservationById(Long id);

    /**
     * 批量删除民宿预订
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsReservationByIds(Long[] ids);
}
