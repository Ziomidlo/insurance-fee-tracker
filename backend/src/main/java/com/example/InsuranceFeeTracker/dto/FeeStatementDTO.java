package com.example.InsuranceFeeTracker.dto;

import com.example.InsuranceFeeTracker.model.Policy;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FeeStatementDTO(
        Long id,
        String office,
        LocalDate createdDate,
        int installment,
        String product,
        String risk,
        BigDecimal collection,
        BigDecimal rate,
        BigDecimal commissionAmount,
        Policy policy
) {
}
