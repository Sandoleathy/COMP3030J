package com.homestay.mapper;

import java.util.List;
import com.homestay.domain.HsStaff;

/**
 * 民宿员工表Mapper接口
 * 
 * @author paru
 * @date 2024-04-25
 */
public interface HsStaffMapper 
{
    /**
     * 查询民宿员工表
     * 
     * @param id 民宿员工表主键
     * @return 民宿员工表
     */
    public HsStaff selectHsStaffById(Long id);

    /**
     * 查询民宿员工表列表
     * 
     * @param hsStaff 民宿员工表
     * @return 民宿员工表集合
     */
    public List<HsStaff> selectHsStaffList(HsStaff hsStaff);

    /**
     * 新增民宿员工表
     * 
     * @param hsStaff 民宿员工表
     * @return 结果
     */
    public int insertHsStaff(HsStaff hsStaff);

    /**
     * 修改民宿员工表
     * 
     * @param hsStaff 民宿员工表
     * @return 结果
     */
    public int updateHsStaff(HsStaff hsStaff);

    /**
     * 删除民宿员工表
     * 
     * @param id 民宿员工表主键
     * @return 结果
     */
    public int deleteHsStaffById(Long id);

    /**
     * 批量删除民宿员工表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHsStaffByIds(Long[] ids);
}
