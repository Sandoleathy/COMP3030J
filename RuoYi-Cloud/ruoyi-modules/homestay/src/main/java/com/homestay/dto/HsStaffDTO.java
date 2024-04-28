package com.homestay.dto;

import com.homestay.domain.HsStaff;
import com.ruoyi.system.api.domain.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HsStaffDTO {
    private SysUser sysUser;
    private HsStaff hsStaff;
}
