package com.statistics.dto;

import com.statistics.utils.Voltage;
import com.statistics.utils.Wind;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class DataFlow {
    List<CompleteEnergySystemData> energySystemDataFlow;
    Wind wind;
    Voltage voltage;

}
