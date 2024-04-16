package com.homestay.dto;

import com.homestay.domain.HsReservation;
import com.homestay.domain.HsRoom;
import com.ruoyi.system.api.domain.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectReservationDTO {
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

    private String roomType;
    private Long roomNumber;
    private Long buildingType;
    private Long bedType;
    private Long roomPrice;

    private Long userId;
    private String userName;
    private String email;
    private String phoneNumber;
}