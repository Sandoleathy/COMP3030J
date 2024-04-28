package com.homestay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectStaffDTO {
    private Long userId;
    private String userName;
    private String email;
    private String phoneNumber;

    private Long id;
    private Long salary;
    private Integer attendance;
}
