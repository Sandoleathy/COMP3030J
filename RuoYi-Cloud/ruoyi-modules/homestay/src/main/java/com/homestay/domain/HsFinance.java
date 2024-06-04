package com.homestay.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿财务表对象 hs_finance
 * 
 * @author paru
 * @date 2024-04-24
 */
public class HsFinance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** time */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "time", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** type（收入/支出） */
    @Excel(name = "type", readConverterExp = "Income = income/expense")
    private String type;

    /** Amount */
    @Excel(name = "Amount")
    private BigDecimal num;

    /** describe */
    @Excel(name = "describe")
    private String description;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setNum(BigDecimal num) 
    {
        this.num = num;
    }

    public BigDecimal getNum() 
    {
        return num;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("time", getTime())
            .append("type", getType())
            .append("num", getNum())
            .append("description", getDescription())
            .toString();
    }
}
