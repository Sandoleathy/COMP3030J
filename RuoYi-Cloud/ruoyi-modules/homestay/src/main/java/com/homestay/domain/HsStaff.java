package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿员工表对象 hs_staff
 * 
 * @author paru
 * @date 2024-04-25
 */
public class HsStaff extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户表主键 */
    @Excel(name = "用户表主键")
    private Long userId;

    /** 工资 */
    @Excel(name = "工资")
    private Long salary;

    /** 考勤 */
    @Excel(name = "考勤")
    private Integer attendance;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSalary(Long salary) 
    {
        this.salary = salary;
    }

    public Long getSalary() 
    {
        return salary;
    }
    public void setAttendance(Integer attendance) 
    {
        this.attendance = attendance;
    }

    public Integer getAttendance() 
    {
        return attendance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("salary", getSalary())
            .append("attendance", getAttendance())
            .toString();
    }
}
