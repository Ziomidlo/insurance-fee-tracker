package com.example.InsuranceFeeTracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DocumentRouterService {
    private final SubmittedFormParser submittedFormParser;


    public void routeDocument(String rawText) {
        if(rawText.contains("Dz. Data Sp")) {
            log.info("Identified Document: Potwierdzenie przekazania druków do centrali");
            submittedFormParser.parseExtractedText(rawText);
        } else if(rawText.contains("Prowizja (Majątek)")) {
            log.info("Identified Document: Prowizja");
        } else {
            log.warn("Unknown document type uploaded.");
        }
    }
}
