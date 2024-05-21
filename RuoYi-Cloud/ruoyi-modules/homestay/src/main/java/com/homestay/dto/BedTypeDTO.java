package com.homestay.dto;

import com.homestay.domain.HsBedImage;
import com.homestay.domain.HsBedType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedTypeDTO {
    private HsBedType hsBedType;
    private List<HsBedImage> hsBedImages;
}
