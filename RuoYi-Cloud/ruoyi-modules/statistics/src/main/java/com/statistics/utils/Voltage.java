package com.statistics.utils;

import lombok.Data;

@Data
public class Voltage {
    public final float voltageWind = 690; // 电压 (V)
    public final float voltageSolar = 12; // 电压 (VDC)
    public final float voltageHydro = 220; // 电压 (V)
}
