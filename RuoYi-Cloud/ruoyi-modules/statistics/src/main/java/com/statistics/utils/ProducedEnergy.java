package com.statistics.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class ProducedEnergy {
    long timeSecond;
    long hydro;
    long solar;
    long wind;

}
