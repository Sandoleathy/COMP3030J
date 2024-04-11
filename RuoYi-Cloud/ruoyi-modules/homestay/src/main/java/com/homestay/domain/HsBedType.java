package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿床类型对象 hs_bed_type
 * 
 * @author paru
 * @date 2024-04-10
 */
public class HsBedType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 床名称 */
    @Excel(name = "床名称")
    private String bedName;

    /** 最大人数 */
    @Excel(name = "最大人数")
    private Long maxPeople;

    /** 宽 */
    @Excel(name = "宽")
    private Long width;

    /** 长 */
    @Excel(name = "长")
    private Long length;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBedName(String bedName) 
    {
        this.bedName = bedName;
    }

    public String getBedName() 
    {
        return bedName;
    }
    public void setMaxPeople(Long maxPeople) 
    {
        this.maxPeople = maxPeople;
    }

    public Long getMaxPeople() 
    {
        return maxPeople;
    }
    public void setWidth(Long width) 
    {
        this.width = width;
    }

    public Long getWidth() 
    {
        return width;
    }
    public void setLength(Long length) 
    {
        this.length = length;
    }

    public Long getLength() 
    {
        return length;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bedName", getBedName())
            .append("maxPeople", getMaxPeople())
            .append("width", getWidth())
            .append("length", getLength())
            .toString();
    }
}
