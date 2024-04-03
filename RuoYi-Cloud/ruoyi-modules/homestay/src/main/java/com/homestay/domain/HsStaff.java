package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿员工对象 hs_staff
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
public class HsStaff extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String employeeName;

    /** 职位（权限） */
    @Excel(name = "职位", readConverterExp = "权=限")
    private String position;

    /** 负责区域 */
    @Excel(name = "负责区域")
    private String department;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private Long contactNumber;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 是否在岗 */
    @Excel(name = "是否在岗")
    private String onDuty;

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
    public void setContactNumber(Long contactNumber) 
    {
        this.contactNumber = contactNumber;
    }

    public Long getContactNumber() 
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
    public void setOnDuty(String onDuty) 
    {
        this.onDuty = onDuty;
    }

    public String getOnDuty() 
    {
        return onDuty;
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
            .toString();
    }
}
