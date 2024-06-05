package com.homestay.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿消费对象 hs_consumption
 * 
 * @author paru
 * @date 2024-04-13
 */
public class HsConsumption extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** Consumption details */
    @Excel(name = "Consumption details")
    private String datail;

    /** price */
    @Excel(name = "price")
    private Long price;

    /** consumption time */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "consumption time", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** booking id */
    @Excel(name = "booking id")
    private Long rid;

    /** user id */
    @Excel(name = "user id")
    private Long uid;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDatail(String datail) 
    {
        this.datail = datail;
    }

    public String getDatail() 
    {
        return datail;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setRid(Long rid) 
    {
        this.rid = rid;
    }

    public Long getRid() 
    {
        return rid;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("datail", getDatail())
            .append("price", getPrice())
            .append("time", getTime())
            .append("rid", getRid())
            .append("uid", getUid())
            .toString();
    }
}
