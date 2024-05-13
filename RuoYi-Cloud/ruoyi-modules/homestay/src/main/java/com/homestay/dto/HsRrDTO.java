package com.homestay.dto;

import com.homestay.domain.HsReservation;
import com.homestay.domain.HsRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HsRrDTO {
    HsReservation hsReservation;
    HsRoom hsRooms;
}
