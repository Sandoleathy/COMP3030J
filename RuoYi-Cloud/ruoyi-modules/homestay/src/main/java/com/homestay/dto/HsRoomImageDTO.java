package com.homestay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HsRoomImageDTO {
    private Long id;
    private String imageDesc;
    private MultipartFile image;
    private Long roomId;
}
