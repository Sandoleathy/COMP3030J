package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿员工对象 hs_staff
 * 
 * @author ruoyi
 * @date 2024-03-26
 */
public class HsStaff extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    @Excel(name = "")
    private String employeeName;

    /**  */
    @Excel(name = "")
    private String position;

    /**  */
    @Excel(name = "")
    private String department;

    /**  */
    @Excel(name = "")
    private String contactNumber;

    /**  */
    @Excel(name = "")
    private String email;

    /**  */
    @Excel(name = "")
    private Integer onDuty;

    /**  */
    @Excel(name = "")
    private Long permissionLevel;

    /**  */
    @Excel(name = "")
    private Long salary;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEmployeeName(String employeeName) 
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() 
    {
        return employeeName;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setContactNumber(String contactNumber) 
    {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() 
    {
        return contactNumber;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setOnDuty(Integer onDuty) 
    {
        this.onDuty = onDuty;
    }

    public Integer getOnDuty() 
    {
        return onDuty;
    }
    public void setPermissionLevel(Long permissionLevel) 
    {
        this.permissionLevel = permissionLevel;
    }

    public Long getPermissionLevel() 
    {
        return permissionLevel;
    }
    public void setSalary(Long salary) 
    {
        this.salary = salary;
    }

    public Long getSalary() 
    {
        return salary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("employeeName", getEmployeeName())
            .append("position", getPosition())
            .append("department", getDepartment())
            .append("contactNumber", getContactNumber())
            .append("email", getEmail())
            .append("onDuty", getOnDuty())
            .append("permissionLevel", getPermissionLevel())
            .append("salary", getSalary())
            .toString();
    }
}
