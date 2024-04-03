package com.homestay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homestay.mapper.HsStaffMapper;
import com.homestay.domain.HsStaff;
import com.homestay.service.IHsStaffService;

/**
 * 民宿员工Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@Service
public class HsStaffServiceImpl implements IHsStaffService 
{
    @Autowired
    private HsStaffMapper hsStaffMapper;

    /**
     * 查询民宿员工
     * 
     * @param id 民宿员工主键
     * @return 民宿员工
     */
    @Override
    public HsStaff selectHsStaffById(Long id)
    {
        return hsStaffMapper.selectHsStaffById(id);
    }

    /**
     * 查询民宿员工列表
     * 
     * @param hsStaff 民宿员工
     * @return 民宿员工
     */
    @Override
    public List<HsStaff> selectHsStaffList(HsStaff hsStaff)
    {
        return hsStaffMapper.selectHsStaffList(hsStaff);
    }

    /**
     * 新增民宿员工
     * 
     * @param hsStaff 民宿员工
     * @return 结果
     */
    @Override
    public int insertHsStaff(HsStaff hsStaff)
    {
        return hsStaffMapper.insertHsStaff(hsStaff);
    }

    /**
     * 修改民宿员工
     * 
     * @param hsStaff 民宿员工
     * @return 结果
     */
    @Override
    public int updateHsStaff(HsStaff hsStaff)
    {
        return hsStaffMapper.updateHsStaff(hsStaff);
    }

    /**
     * 批量删除民宿员工
     * 
     * @param ids 需要删除的民宿员工主键
     * @return 结果
     */
    @Override
    public int deleteHsStaffByIds(Long[] ids)
    {
        return hsStaffMapper.deleteHsStaffByIds(ids);
    }

    /**
     * 删除民宿员工信息
     * 
     * @param id 民宿员工主键
     * @return 结果
     */
    @Override
    public int deleteHsStaffById(Long id)
    {
        return hsStaffMapper.deleteHsStaffById(id);
    }
}
