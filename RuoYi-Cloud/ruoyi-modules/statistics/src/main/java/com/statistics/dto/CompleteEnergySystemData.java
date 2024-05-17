package com.statistics.dto;

import lombok.Data;

@Data
public class CompleteEnergySystemData {
    // 风力发电系统
    private float generatorSpeed;  // 发电机转速 (rpm)
    private float generatorTorque; // 发电机扭矩 (Nm)

    private float currentWind;     // 电流 (I)
    private float powerWind;       // 功率 (W)
    private float windEnergyConversionEfficiency; //风能转换效率
    private float theoreticalMaxPower; //理论最大功率
    private float windSpeed; //风力
    private long energyProducedWind; // 最终产生的能量 (KWh)
    private long carbonReductionWind; // 减少的碳排放量



    // 太阳能发电系统
    private float lightIntensity;
    private float deviceTemperature; // 设备温度 (°C)

    private float currentSolar;    // 电流 (I)
    private float powerSolar;      // 功率 (W)
    private long energyProducedSolar; // 发电量
    private long carbonReductionSolar; // 减少的碳排放量

    // 落差水力发电系统
    private float waterFlow;      // 水流量 (litres/sec)
    private float flowSpeed;      // 流速 (m/s)
    private float upperWaterLevel; // 上游水位 (m)
    private float lowerWaterLevel; // 下游水位 (m)
    private float turbineSpeed;   // 水轮机转速 (rpm)

    private float currentHydro;   // 电流 (A)
    private float powerHydro;     // 功率 (W)
    private long carbonReductionHydro; // 减少的碳排放量
    private long energyProducedHydro;

    private long timeSeconds;












}

