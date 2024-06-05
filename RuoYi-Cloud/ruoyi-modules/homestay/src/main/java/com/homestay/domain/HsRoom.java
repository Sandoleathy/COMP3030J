package com.homestay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿房间对象 hs_room
 * 
 * @author paru
 * @date 2024-04-12
 */
public class HsRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** Room type */
    @Excel(name = "Room type")
    private String roomType;

    /** room number */
    @Excel(name = "room number")
    private Long roomNumber;

    /** Building type */
    @Excel(name = "Building type")
    private Long buildingType;

    /** bed type */
    @Excel(name = "bed type")
    private Long bedType;

    /** price */
    @Excel(name = "price")
    private Long roomPrice;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRoomType(String roomType) 
    {
        this.roomType = roomType;
    }

    public String getRoomType() 
    {
        return roomType;
    }
    public void setRoomNumber(Long roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public Long getRoomNumber() 
    {
        return roomNumber;
    }
    public void setBuildingType(Long buildingType) 
    {
        this.buildingType = buildingType;
    }

    public Long getBuildingType() 
    {
        return buildingType;
    }
    public void setBedType(Long bedType) 
    {
        this.bedType = bedType;
    }

    public Long getBedType() 
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roomType", getRoomType())
            .append("roomNumber", getRoomNumber())
            .append("buildingType", getBuildingType())
            .append("bedType", getBedType())
            .append("roomPrice", getRoomPrice())
            .toString();
    }
}
