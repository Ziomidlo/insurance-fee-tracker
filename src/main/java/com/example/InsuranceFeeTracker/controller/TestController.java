package com.example.InsuranceFeeTracker.controller;

import com.example.InsuranceFeeTracker.model.SubmittedForm;
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
public class TestController {

    private final PdfExtractionService pdfExtractionService;
    private final SubmittedFormRepository submittedFormRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTestPdf(@RequestParam("file")MultipartFile file) {
        String rawText = pdfExtractionService.extractRawText(file);
        return ResponseEntity.ok("File processed!" + rawText);
    }

    @GetMapping("/forms")
    public ResponseEntity<List<SubmittedForm>> getAllForms() {
        return ResponseEntity.ok(submittedFormRepository.findAll());
    }
}
