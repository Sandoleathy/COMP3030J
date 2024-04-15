package com.homestay.dto;

import com.homestay.domain.HsRoom;
import com.homestay.domain.HsRoomImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomDTO {
    private HsRoom hsRoom;
    private List<HsRoomImage> hsRoomImages;
}
