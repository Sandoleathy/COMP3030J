package com.homestay.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿房间对象 hs_room
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
public class HsRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 房间号 */
    @Excel(name = "房间号")
    private Long roomNumber;

    /** 房间类型 */
    @Excel(name = "房间类型")
    private String roomType;

    /** 床类型 */
    @Excel(name = "床类型")
    private String bedType;

    /** 价格 */
    @Excel(name = "价格")
    private Long roomPrice;

    /** 状态 */
    @Excel(name = "状态")
    private String roomStatus;

    /** 最大人数 */
    @Excel(name = "最大人数")
    private Long maxOccupancy;

    /** 设施 */
    @Excel(name = "设施")
    private String amenities;

    /** 描述 */
    @Excel(name = "描述")
    private String roomDescription;

    /** 入住时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkIntTime;

    /** 搬出时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "搬出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkOutTime;

    /** 客户要求 */
    @Excel(name = "客户要求")
    private String customerDemand;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRoomNumber(Long roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public Long getRoomNumber() 
    {
        return roomNumber;
    }
    public void setRoomType(String roomType) 
    {
        this.roomType = roomType;
    }

    public String getRoomType() 
    {
        return roomType;
    }
    public void setBedType(String bedType) 
    {
        this.bedType = bedType;
    }

    public String getBedType() 
    {
        return bedType;
    }
    public void setRoomPrice(Long roomPrice) 
    {
        this.roomPrice = roomPrice;
    }

    public Long getRoomPrice() 
    {
        return roomPrice;
    }
    public void setRoomStatus(String roomStatus) 
    {
        this.roomStatus = roomStatus;
    }

    public String getRoomStatus() 
    {
        return roomStatus;
    }
    public void setMaxOccupancy(Long maxOccupancy) 
    {
        this.maxOccupancy = maxOccupancy;
    }

    public Long getMaxOccupancy() 
    {
        return maxOccupancy;
    }
    public void setAmenities(String amenities) 
    {
        this.amenities = amenities;
    }

    public String getAmenities() 
    {
        return amenities;
    }
    public void setRoomDescription(String roomDescription) 
    {
        this.roomDescription = roomDescription;
    }

    public String getRoomDescription() 
    {
        return roomDescription;
    }
    public void setCheckIntTime(Date checkIntTime) 
    {
        this.checkIntTime = checkIntTime;
    }

    public Date getCheckIntTime() 
    {
        return checkIntTime;
    }
    public void setCheckOutTime(Date checkOutTime) 
    {
        this.checkOutTime = checkOutTime;
    }

    public Date getCheckOutTime() 
    {
        return checkOutTime;
    }
    public void setCustomerDemand(String customerDemand) 
    {
        this.customerDemand = customerDemand;
    }

    public String getCustomerDemand() 
    {
        return customerDemand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roomNumber", getRoomNumber())
            .append("roomType", getRoomType())
            .append("bedType", getBedType())
            .append("roomPrice", getRoomPrice())
            .append("roomStatus", getRoomStatus())
            .append("maxOccupancy", getMaxOccupancy())
            .append("amenities", getAmenities())
            .append("roomDescription", getRoomDescription())
            .append("checkIntTime", getCheckIntTime())
            .append("checkOutTime", getCheckOutTime())
            .append("customerDemand", getCustomerDemand())
            .toString();
    }
}
