package com.example.InsuranceFeeTracker.controller;

import com.example.InsuranceFeeTracker.service.PdfExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("api/test")
@RestController
public class TestController {

    private final PdfExtractionService pdfExtractionService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTestPdf(@RequestParam("file")MultipartFile file) {
        String rawText = pdfExtractionService.extractRawText(file);
        return ResponseEntity.ok("File processed!" + rawText);
    }
}
