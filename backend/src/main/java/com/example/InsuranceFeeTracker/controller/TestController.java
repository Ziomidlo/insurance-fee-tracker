package com.example.InsuranceFeeTracker.controller;

import com.example.InsuranceFeeTracker.dto.FeeStatementDTO;
import com.example.InsuranceFeeTracker.dto.PolicyResponseDTO;
import com.example.InsuranceFeeTracker.dto.SubmittedFormDTO;
import com.example.InsuranceFeeTracker.mapper.FeeStatementMapper;
import com.example.InsuranceFeeTracker.mapper.PolicyMapper;
import com.example.InsuranceFeeTracker.mapper.SubmittedFormMapper;
import com.example.InsuranceFeeTracker.model.FeeStatement;
import com.example.InsuranceFeeTracker.model.Policy;
import com.example.InsuranceFeeTracker.model.SubmittedForm;
import com.example.InsuranceFeeTracker.repository.FeeStatementRepository;
import com.example.InsuranceFeeTracker.repository.PolicyRepository;
import com.example.InsuranceFeeTracker.repository.SubmittedFormRepository;
import com.example.InsuranceFeeTracker.service.PdfExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/test")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    private final PdfExtractionService pdfExtractionService;
    private final SubmittedFormRepository submittedFormRepository;
    private final FeeStatementRepository feeStatementRepository;
    private final PolicyRepository policyRepository;
    private final PolicyMapper policyMapper;
    private final FeeStatementMapper feeStatementMapper;
    private final SubmittedFormMapper submittedFormMapper;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTestPdf(@RequestParam("file")MultipartFile file) {
        String rawText = pdfExtractionService.extractRawText(file);
        return ResponseEntity.ok("File processed!" + rawText);
    }

    @GetMapping("/forms")
    public ResponseEntity<List<SubmittedFormDTO>> getAllForms() {

        List<SubmittedForm> rawSubmittedForms = submittedFormRepository.findAll();

        List<SubmittedFormDTO> dtoList = rawSubmittedForms.stream()
                .map(submittedFormMapper::mapToDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/feeStatements")
    public ResponseEntity<List<FeeStatementDTO>> getAllFeeStatements() {
        List<FeeStatement> rawFeeStatements = feeStatementRepository.findAll();

        List<FeeStatementDTO> dtoList = rawFeeStatements.stream()
                .map(feeStatementMapper::mapToDto)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/policies")
    public ResponseEntity<List<PolicyResponseDTO>> getAllPolicies() {
        List<Policy> rawPolicies = policyRepository.findAll();

        List<PolicyResponseDTO> dtoList = rawPolicies.stream()
                .map(policyMapper::mapToDto)
                .toList();

        return ResponseEntity.ok(dtoList);
    }
}
