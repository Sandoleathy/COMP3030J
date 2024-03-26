package com.homestay.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 房间预定对象 hs_reservation
 * 
 * @author ruoyi
 * @date 2024-03-26
 */
public class HsReservation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    @Excel(name = "")
    private String contactInformation;

    /**  */
    @Excel(name = "")
    private Long roomNumber;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkinDate;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkoutDate;

    /**  */
    @Excel(name = "")
    private Long numberOfRooms;

    /**  */
    @Excel(name = "")
    private Long numberOfGuests;

    /**  */
    @Excel(name = "")
    private String reservationStatus;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reservationTime;

    /**  */
    @Excel(name = "")
    private String specialRequests;

    /** 民宿房间信息 */
    private List<HsRoom> hsRoomList;

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
    public void setCheckinDate(Date checkinDate) 
    {
        this.checkinDate = checkinDate;
    }

    public Date getCheckinDate() 
    {
        return checkinDate;
    }
    public void setCheckoutDate(Date checkoutDate) 
    {
        this.checkoutDate = checkoutDate;
    }

    public Date getCheckoutDate() 
    {
        return checkoutDate;
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
    public void setSpecialRequests(String specialRequests) 
    {
        this.specialRequests = specialRequests;
    }

    public String getSpecialRequests() 
    {
        return specialRequests;
    }

    public List<HsRoom> getHsRoomList()
    {
        return hsRoomList;
    }

    public void setHsRoomList(List<HsRoom> hsRoomList)
    {
        this.hsRoomList = hsRoomList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("contactInformation", getContactInformation())
            .append("roomNumber", getRoomNumber())
            .append("checkinDate", getCheckinDate())
            .append("checkoutDate", getCheckoutDate())
            .append("numberOfRooms", getNumberOfRooms())
            .append("numberOfGuests", getNumberOfGuests())
            .append("reservationStatus", getReservationStatus())
            .append("reservationTime", getReservationTime())
            .append("specialRequests", getSpecialRequests())
            .append("hsRoomList", getHsRoomList())
            .toString();
    }
}
