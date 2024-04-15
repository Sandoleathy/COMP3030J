package com.homestay.dto;

import com.homestay.domain.HsBedImage;
import com.homestay.domain.HsBedType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectBedTypeDTO {
    private String bedName;
    private Long maxPeople;
    private Long width;
    private Long length;
    private String imageDesc;
    private Long bedId;
}
