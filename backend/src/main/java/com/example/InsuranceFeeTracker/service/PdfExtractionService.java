package com.example.InsuranceFeeTracker.service;

import com.example.InsuranceFeeTracker.model.Enum.PaymentMethod;
import com.example.InsuranceFeeTracker.model.SubmittedForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Slf4j
public class PdfExtractionService {

    private final DocumentRouterService documentRouterService;


    public String extractRawText(MultipartFile file) {

        if(file.isEmpty()){
            throw new IllegalArgumentException("The uploaded file is empty!");
        }

        log.info("Starting text extraction for file: {}", file.getOriginalFilename());


        //Try-With-Resources (The Safe Memory Manager)
        try (InputStream inputStream = file.getInputStream();
             RandomAccessReadBuffer randomAccessReadBuffer = new RandomAccessReadBuffer(inputStream);
             PDDocument document = Loader.loadPDF(randomAccessReadBuffer)) {

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            String rawText = pdfTextStripper.getText(document);

            log.info("Successfully extracted {} characters. ", rawText.length());
            System.out.println("\n--- START EXTRACT RAW PDF TEXT ---\n" + rawText + "\n --- END EXTRACT RAW PDF TEXT ---\n");

            documentRouterService.routeDocument(rawText);

            return rawText;

        } catch (IOException e) {
            log.error("Failed to parse the PDF file" , e);
            throw new RuntimeException(e);
        }

    }

}
