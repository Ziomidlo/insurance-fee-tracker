package com.example.InsuranceFeeTracker.service;

import com.example.InsuranceFeeTracker.model.Enum.PaymentMethod;
import com.example.InsuranceFeeTracker.model.SubmittedForm;
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

@Service
@Slf4j
public class PdfExtractionService {

    private static final List<String> KNOWN_COMPANIES = List.of("COMPENSA" , "HESTIA MTU", "LINK4",
            "PZU", "WARTA", "WIENER");
    private final PolicyProcessingService policyProcessingService;

    public PdfExtractionService(PolicyProcessingService policyProcessingService) {
        this.policyProcessingService = policyProcessingService;
    }

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

            parseExtractedText(rawText);

            return rawText;

        } catch (IOException e) {
            log.error("Failed to parse the PDF file" , e);
            throw new RuntimeException(e);
        }

    }

    public void parseExtractedText(String rawPdfText) {
        String regex = "^S\\s+(\\d{4}-\\d{2}-\\d{2})\\s+(.*?)\\s+(\\d+,\\d{2})\\s+(\\d+,\\d{2})\\s+(G|PT)$";

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

        Matcher matcher = pattern.matcher(rawPdfText);

        while(matcher.find()) {

            //Extracting data with matcher

            String dateStr = matcher.group(1);
            String policyDetails = matcher.group(2);
            String collectionStr = matcher.group(3);
            String cashStr = matcher.group(4);
            String paymentStr = matcher.group(5);

            //Convert to java objects

            String[] parsedData = extractCompanyAndPolicy(policyDetails);
            String company = parsedData[0];
            String fullPolicyString = parsedData[1];

            LocalDate confirmedDate = LocalDate.parse(dateStr);

            BigDecimal collection = new BigDecimal(collectionStr.replace("," , "."));
            BigDecimal cash = new BigDecimal(cashStr.replace("," , "."));

            PaymentMethod paymentMethod = paymentStr.equals("G") ? PaymentMethod.CASH : PaymentMethod.TRANSFER;

            log.info("Found Form -> Date {}, Collection: {} PLN, Cash: {} PLN, PaymentMethod: {}",
                    confirmedDate, collection, cash, paymentMethod);

            //Build the SubmittedForm instance

            SubmittedForm submittedForm = SubmittedForm.builder()
                    .confirmedDate(confirmedDate)
                    .collection(collection)
                    .cash(cash)
                    .paymentMethod(paymentMethod)
                    .build();

            //Send the new created instance to process policy details

            policyProcessingService.processSubmittedForm("", "", fullPolicyString, company, submittedForm);
        }
    }

    private String[] extractCompanyAndPolicy(String policyDetails) {
        String foundCompany = "UNKNOWN";
        String leftoverPolicy = policyDetails;

        for(String company : KNOWN_COMPANIES) {
            if(policyDetails.startsWith(company)) {
                foundCompany = company;

                leftoverPolicy = policyDetails.substring(company.length()).trim();
                break;
            }
        }
        return new String[]{foundCompany, leftoverPolicy};
    }
}
