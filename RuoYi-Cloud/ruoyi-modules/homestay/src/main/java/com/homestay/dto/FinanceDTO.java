package com.homestay.dto;

import com.homestay.domain.HsFinance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceDTO {
    private Map<String, Float> results;
    private List<HsFinance> finances;
}
