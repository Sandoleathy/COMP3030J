package com.homestay.dto;

import com.homestay.domain.HsConsumption;
import com.homestay.domain.HsReservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionDTO {
    private HsReservation hsReservation;
    private String userName;
    private String email;
    private String phoneNumber;
    private List<HsConsumption> hsConsumptions;
}
