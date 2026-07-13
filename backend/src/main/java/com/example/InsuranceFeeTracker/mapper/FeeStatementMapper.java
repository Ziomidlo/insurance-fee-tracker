package com.example.InsuranceFeeTracker.mapper;

import com.example.InsuranceFeeTracker.dto.FeeStatementDTO;
import com.example.InsuranceFeeTracker.model.FeeStatement;
import com.example.InsuranceFeeTracker.model.Policy;
import org.springframework.stereotype.Component;

@Component
public class FeeStatementMapper {

    public FeeStatementDTO mapToDto(FeeStatement feeStatement) {
        return new FeeStatementDTO(
                feeStatement.getId(),
                feeStatement.getOffice(),
                feeStatement.getCreatedDate(),
                feeStatement.getInstallment(),
                feeStatement.getProduct(),
                feeStatement.getRisk(),
                feeStatement.getCollection(),
                feeStatement.getRate(),
                feeStatement.getCommissionAmount(),
                feeStatement.getPolicy()
        );
    }
}
