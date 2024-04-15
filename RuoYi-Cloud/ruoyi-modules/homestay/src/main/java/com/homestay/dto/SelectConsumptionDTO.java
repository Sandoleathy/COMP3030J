package com.homestay.dto;

import com.homestay.domain.HsConsumption;
import com.homestay.domain.HsReservation;
import com.ruoyi.system.api.domain.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectConsumptionDTO {
    private String contactInformation;
    private Date checkinTime;
    private Date checkoutTime;
    private Long numberOfGuests;
    private Long numberOfRooms;
    private Date reservationTime;
    private String requests;
    private Long totalPrice;
    private Integer pay;
    private String reservationStatus;

    private String userName;
    private String email;
    private String phoneNumber;

    private String datail;
    private Long price;
    private Date time;
    private Long rid;
    private Long uid;
}
