package com.homestay.dto;

import com.homestay.domain.HsReservation;
import com.homestay.domain.HsRoom;
import com.ruoyi.system.api.domain.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private  HsReservation hsReservation;
    private List<HsRoom> rooms;
    private List<SysUser> users;
}
