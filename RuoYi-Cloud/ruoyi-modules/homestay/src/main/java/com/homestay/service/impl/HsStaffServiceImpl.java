package com.homestay.service.impl;

import com.homestay.domain.HsStaff;
import com.homestay.dto.HsStaffDTO;
import com.homestay.dto.SelectStaffDTO;
import com.homestay.mapper.HsFinanceMapper;
import com.homestay.mapper.HsGuestMapper;
import com.homestay.mapper.HsStaffMapper;
import com.homestay.service.IHsStaffService;
import com.ruoyi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 民宿员工表Service业务层处理
 *
 * @author paru
 * @date 2024-04-25
 */
@Service
public class HsStaffServiceImpl implements IHsStaffService {
    @Autowired
    private HsStaffMapper hsStaffMapper;

    @Autowired
    private HsGuestMapper hsGuestMapper;

    @Autowired
    private HsFinanceMapper hsFinanceMapper;

    /**
     * 查询民宿员工表
     *
     * @param id 民宿员工表主键
     * @return 民宿员工表
     */
    @Override
    public HsStaffDTO selectHsStaffById(Long id) {
        HsStaff hsStaff = hsStaffMapper.selectHsStaffById(id);
        SysUser sysUser = hsGuestMapper.selectSysUserById(hsStaff.getUserId());
        return new HsStaffDTO(sysUser, hsStaff);
    }

    /**
     * 查询民宿员工表列表
     *
     * @param selectStaffDTO 民宿员工表DTO
     * @return 民宿员工表
     */
    @Override
    public List<HsStaffDTO> selectHsStaffList(SelectStaffDTO selectStaffDTO) {
        List<HsStaffDTO> dtos = new ArrayList<>();
        SysUser user = new SysUser();
        user.setUserId(selectStaffDTO.getUserId());
        user.setUserName(selectStaffDTO.getUserName());
        user.setEmail(selectStaffDTO.getEmail());
        user.setPhonenumber(selectStaffDTO.getPhoneNumber());

        HsStaff hsStaff = new HsStaff();
        hsStaff.setId(selectStaffDTO.getId());
        hsStaff.setSalary(selectStaffDTO.getSalary());
        hsStaff.setAttendance(selectStaffDTO.getAttendance());

        List<SysUser> users = hsGuestMapper.selectSysUserByUser(user);
        List<HsStaff> staffs = hsStaffMapper.selectHsStaffList(hsStaff);
        for (SysUser sysUser : users) {
            for (HsStaff staff : staffs) {
                if (sysUser.getUserId().equals(staff.getUserId())) {
                    dtos.add(new HsStaffDTO(sysUser, staff));
                }
            }
        }
        return dtos;
    }

    /**
     * 新增民宿员工表
     *
     * @param hsStaffDTO 民宿员工表
     * @return 结果
     */
    @Override
    public int insertHsStaff(HsStaffDTO hsStaffDTO) {
        long id = hsGuestMapper.insertSysUser(hsStaffDTO.getSysUser());
        HsStaff hsStaff = hsStaffDTO.getHsStaff();
        hsStaff.setUserId(id);
        return hsStaffMapper.insertHsStaff(hsStaff);
    }

    /**
     * 修改民宿员工表
     *
     * @param hsStaff 民宿员工表
     * @return 结果
     */
    @Override
    public int updateHsStaff(HsStaff hsStaff) {
        return hsStaffMapper.updateHsStaff(hsStaff);
    }

    /**
     * 批量删除民宿员工表
     *
     * @param ids 需要删除的民宿员工表主键
     * @return 结果
     */
    @Override
    public int deleteHsStaffByIds(Long[] ids) {
        return hsStaffMapper.deleteHsStaffByIds(ids);
    }

    /**
     * 删除民宿员工表信息
     *
     * @param id 民宿员工表主键
     * @return 结果
     */
    @Override
    public int deleteHsStaffById(Long id) {
        return hsStaffMapper.deleteHsStaffById(id);
    }

    /**
     * 选择指定员工工资
     *
     * @param id 民宿员工表主键
     * @return 结果
     */
    @Override
    public float selectSalaryById(Long id) {
        HsStaff staff = hsStaffMapper.selectHsStaffById(id);
        return staff.getSalary();
    }

    @Override
    public float selectSalaryByIds(Long[] ids) {
        float[] salaries = new float[ids.length];
        float result = 0;
        for (Long id : ids) {
            result += selectSalaryById(id);
        }
        return result;
    }

    @Override
    public float selectAllSalary() {
        float result = 0;
        List<HsStaff> staffs = hsStaffMapper.selectHsStaffList(new HsStaff());
        for (HsStaff hsStaff : staffs) {
            result += hsStaff.getSalary();
        }
        return result;
    }
}
