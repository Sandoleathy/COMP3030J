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
 * @author paru
 * @date 2024-04-09
 */
public class HsReservation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 客户联系方式 */
    @Excel(name = "客户联系方式")
    private String contactInformation;

    /** 入住时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkinTime;

    /** 退房时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退房时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkoutTime;

    /** 客户数量 */
    @Excel(name = "客户数量")
    private Long numberOfGuests;

    /** 房间数量 */
    @Excel(name = "房间数量")
    private Long numberOfRooms;

    /** 订单创建/结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单创建/结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reservationTime;

    /** 备注 */
    @Excel(name = "备注")
    private String requests;

    /** 总价格 */
    @Excel(name = "总价格")
    private Long totalPrice;

    /** 是否支付 */
    @Excel(name = "是否支付")
    private Integer pay;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String reservationStatus;

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
    public void setCheckinTime(Date checkinTime) 
    {
        this.checkinTime = checkinTime;
    }

    public Date getCheckinTime() 
    {
        return checkinTime;
    }
    public void setCheckoutTime(Date checkoutTime) 
    {
        this.checkoutTime = checkoutTime;
    }

    public Date getCheckoutTime() 
    {
        return checkoutTime;
    }
    public void setNumberOfGuests(Long numberOfGuests) 
    {
        this.numberOfGuests = numberOfGuests;
    }

    public Long getNumberOfGuests() 
    {
        return numberOfGuests;
    }
    public void setNumberOfRooms(Long numberOfRooms) 
    {
        this.numberOfRooms = numberOfRooms;
    }

    public Long getNumberOfRooms() 
    {
        return numberOfRooms;
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
    public void setTotalPrice(Long totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public Long getTotalPrice() 
    {
        return totalPrice;
    }
    public void setPay(Integer pay) 
    {
        this.pay = pay;
    }

    public Integer getPay() 
    {
        return pay;
    }
    public void setReservationStatus(String reservationStatus) 
    {
        this.reservationStatus = reservationStatus;
    }

    public String getReservationStatus() 
    {
        return reservationStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("contactInformation", getContactInformation())
            .append("checkinTime", getCheckinTime())
            .append("checkoutTime", getCheckoutTime())
            .append("numberOfGuests", getNumberOfGuests())
            .append("numberOfRooms", getNumberOfRooms())
            .append("reservationTime", getReservationTime())
            .append("requests", getRequests())
            .append("totalPrice", getTotalPrice())
            .append("pay", getPay())
            .append("reservationStatus", getReservationStatus())
            .toString();
    }
}
