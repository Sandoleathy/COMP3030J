package com.homestay.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 民宿预订对象 hs_reservation
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
public class HsReservation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 客户联系方式 */
    @Excel(name = "客户联系方式")
    private String contactInformation;

    /** 房间号 */
    @Excel(name = "房间号")
    private Long roomNumber;

    /** 入住时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkinTime;

    /** 房间数 */
    @Excel(name = "房间数")
    private Long numberOfRooms;

    /** 客户数 */
    @Excel(name = "客户数")
    private Long numberOfGuests;

    /** 预订状态 */
    @Excel(name = "预订状态")
    private String reservationStatus;

    /** 预订时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预订时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reservationTime;

    /** 客户要求 */
    @Excel(name = "客户要求")
    private String requests;

    /** 搬出时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "搬出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkoutTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setContactInformation(String contactInformation) 
    {
        this.contactInformation = contactInformation;
    }

    public String getContactInformation() 
    {
        return contactInformation;
    }
    public void setRoomNumber(Long roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public Long getRoomNumber() 
    {
        return roomNumber;
    }
    public void setCheckinTime(Date checkinTime) 
    {
        this.checkinTime = checkinTime;
    }

    public Date getCheckinTime() 
    {
        return checkinTime;
    }
    public void setNumberOfRooms(Long numberOfRooms) 
    {
        this.numberOfRooms = numberOfRooms;
    }

    public Long getNumberOfRooms() 
    {
        return numberOfRooms;
    }
    public void setNumberOfGuests(Long numberOfGuests) 
    {
        this.numberOfGuests = numberOfGuests;
    }

    public Long getNumberOfGuests() 
    {
        return numberOfGuests;
    }
    public void setReservationStatus(String reservationStatus) 
    {
        this.reservationStatus = reservationStatus;
    }

    public String getReservationStatus() 
    {
        return reservationStatus;
    }
    public void setReservationTime(Date reservationTime) 
    {
        this.reservationTime = reservationTime;
    }

    public Date getReservationTime() 
    {
        return reservationTime;
    }
    public void setRequests(String requests) 
    {
        this.requests = requests;
    }

    public String getRequests() 
    {
        return requests;
    }
    public void setCheckoutTime(Date checkoutTime) 
    {
        this.checkoutTime = checkoutTime;
    }

    public Date getCheckoutTime() 
    {
        return checkoutTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("contactInformation", getContactInformation())
            .append("roomNumber", getRoomNumber())
            .append("checkinTime", getCheckinTime())
            .append("numberOfRooms", getNumberOfRooms())
            .append("numberOfGuests", getNumberOfGuests())
            .append("reservationStatus", getReservationStatus())
            .append("reservationTime", getReservationTime())
            .append("requests", getRequests())
            .append("checkoutTime", getCheckoutTime())
            .toString();
    }
}
