package com.homestay.dto;

import com.homestay.domain.HsRoom;
import com.homestay.domain.HsRoomImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectRoomDTO {
    private String roomType;
    private Long roomNumber;
    private Long buildingType;
    private Long bedType;
    private Long roomPrice;
    private String imageDesc;
    private Long roomId;
}
