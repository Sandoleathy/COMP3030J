package com.homestay.service;

import java.util.List;
import com.homestay.domain.HsStaff;

/**
 * 民宿员工Service接口
 * 
 * @author ruoyi
 * @date 2024-03-26
 */
public interface IHsStaffService 
{
    /**
     * 查询民宿员工
     * 
     * @param id 民宿员工主键
     * @return 民宿员工
     */
    public HsStaff selectHsStaffById(Long id);

    /**
     * 查询民宿员工列表
     * 
     * @param hsStaff 民宿员工
     * @return 民宿员工集合
     */
    public List<HsStaff> selectHsStaffList(HsStaff hsStaff);

    /**
     * 新增民宿员工
     * 
     * @param hsStaff 民宿员工
     * @return 结果
     */
    public int insertHsStaff(HsStaff hsStaff);

    /**
     * 修改民宿员工
     * 
     * @param hsStaff 民宿员工
     * @return 结果
     */
    public int updateHsStaff(HsStaff hsStaff);

    /**
     * 批量删除民宿员工
     * 
     * @param ids 需要删除的民宿员工主键集合
     * @return 结果
     */
    public int deleteHsStaffByIds(Long[] ids);

    /**
     * 删除民宿员工信息
     * 
     * @param id 民宿员工主键
     * @return 结果
     */
    public int deleteHsStaffById(Long id);
}
