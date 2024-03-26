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
 * @date 2024-03-26
 */
public class HsRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /**  */
    @Excel(name = "")
    private Long roomNumber;

    /**  */
    @Excel(name = "")
    private String roomType;

    /**  */
    @Excel(name = "")
    private String bedType;

    /**  */
    @Excel(name = "")
    private Long roomPrice;

    /**  */
    @Excel(name = "")
    private String roomStatus;

    /**  */
    @Excel(name = "")
    private Long maxOccupancy;

    /**  */
    @Excel(name = "")
    private String amenities;

    /**  */
    @Excel(name = "")
    private String roomDescription;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkInTime;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkOutTime;

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
    public void setCheckInTime(Date checkInTime) 
    {
        this.checkInTime = checkInTime;
    }

    public Date getCheckInTime() 
    {
        return checkInTime;
    }
    public void setCheckOutTime(Date checkOutTime) 
    {
        this.checkOutTime = checkOutTime;
    }

    public Date getCheckOutTime() 
    {
        return checkOutTime;
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
            .append("checkInTime", getCheckInTime())
            .append("checkOutTime", getCheckOutTime())
            .toString();
    }
}
