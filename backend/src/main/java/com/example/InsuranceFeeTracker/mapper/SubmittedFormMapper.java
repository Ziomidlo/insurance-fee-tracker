package com.example.InsuranceFeeTracker.mapper;

import com.example.InsuranceFeeTracker.dto.SubmittedFormDTO;
import com.example.InsuranceFeeTracker.model.SubmittedForm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubmittedFormMapper {

    public SubmittedFormDTO mapToDto(SubmittedForm submittedForm) {
        return new SubmittedFormDTO(
                submittedForm.getId(),
                submittedForm.getConfirmedDate(),
                submittedForm.getCollection(),
                submittedForm.getCash(),
                submittedForm.getPaymentMethod(),
                submittedForm.getPolicy()
        );
    }
}
