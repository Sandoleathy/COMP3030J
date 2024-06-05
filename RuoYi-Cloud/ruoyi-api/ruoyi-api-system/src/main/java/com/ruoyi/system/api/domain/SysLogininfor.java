package com.ruoyi.system.api.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.annotation.Excel.ColumnType;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 系统访问记录表 sys_logininfor
 * 
 * @author ruoyi
 */
public class SysLogininfor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "serial number", cellType = ColumnType.NUMERIC)
    private Long infoId;

    /** user account */
    @Excel(name = "user account")
    private String userName;

    /** state 0成功 1失败 */
    @Excel(name = "state", readConverterExp = "0=success, 1=failure")
    private String status;

    /** address */
    @Excel(name = "address")
    private String ipaddr;

    /** describe */
    @Excel(name = "describe")
    private String msg;

    /** interview time */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "interview time", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date accessTime;

    public Long getInfoId()
    {
        return infoId;
    }

    public void setInfoId(Long infoId)
    {
        this.infoId = infoId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Date getAccessTime()
    {
        return accessTime;
    }

    public void setAccessTime(Date accessTime)
    {
        this.accessTime = accessTime;
    }
}