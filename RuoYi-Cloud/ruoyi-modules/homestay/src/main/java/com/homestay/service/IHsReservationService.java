package com.homestay.service;

import java.util.List;
import com.homestay.domain.HsReservation;
import com.homestay.dto.AddReservationDTO;
import com.homestay.dto.ReservationDTO;
import com.homestay.dto.SelectReservationDTO;

/**
 * 民宿预订Service接口
 * 
 * @author paru
 * @date 2024-04-09
 */
public interface IHsReservationService 
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
    public List<ReservationDTO> select(HsReservation hsReservation);

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
     * 批量删除民宿预订
     * 
     * @param ids 需要删除的民宿预订主键集合
     * @return 结果
     */
    public int[] deleteHsReservationByIds(Long[] ids);

    /**
     * 删除民宿预订信息
     * 
     * @param id 民宿预订主键
     * @return 结果
     */
    public int deleteHsReservationById(Long id);


    /**
     * 更新预订状态为“已支付”
     *
     * @param id 民宿预订主键
     * @return 结果
     */
    public int updateReservationStatusToPaid(Long id,String description);

}
