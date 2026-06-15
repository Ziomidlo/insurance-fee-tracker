package com.example.InsuranceFeeTracker.service;

import com.example.InsuranceFeeTracker.model.Enum.PaymentMethod;
import com.example.InsuranceFeeTracker.model.SubmittedForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
@Slf4j
public class SubmittedFormParser {

    private static final List<String> KNOWN_COMPANIES = List.of("COMPENSA" , "HESTIA MTU", "LINK4",
            "PZU", "WARTA", "WIENER", "INTERRISK");

    private final PolicyProcessingService policyProcessingService;


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
            String dirtyPolicyString = parsedData[1];
            String cleanPolicyString = cleanDoubledPolicyNumber(dirtyPolicyString);

            cleanPolicyString = cleanPolicyString.replaceAll("\\(W\\)", "")
                    .replaceAll("\\s+", " ")
                    .trim();


            String policyRegex = "\\s+(.*?)";
            String [] fullPolicyNumber = cleanPolicyString.split(policyRegex);
            String policySeries = fullPolicyNumber[0];
            String policyNumber = fullPolicyNumber[1];


            LocalDate confirmedDate = LocalDate.parse(dateStr);

            BigDecimal collection = new BigDecimal(collectionStr.replace("," , "."));
            BigDecimal cash = new BigDecimal(cashStr.replace("," , "."));

            PaymentMethod paymentMethod = paymentStr.equals("G") ? PaymentMethod.CASH : PaymentMethod.TRANSFER;

            log.info("Found Form -> Company {}, CleanPolicyString {}, policySeries {}, policyNumber {},  Date {}, Collection: {} PLN, Cash: {} PLN, PaymentMethod: {}",
                    company, cleanPolicyString, policySeries, policyNumber, confirmedDate, collection, cash, paymentMethod);


            //Build the SubmittedForm instance


            SubmittedForm submittedForm = SubmittedForm.builder()
                    .confirmedDate(confirmedDate)
                    .collection(collection)
                    .cash(cash)
                    .paymentMethod(paymentMethod)
                    .build();

            //Send the new created instance to process policy details

            policyProcessingService.processSubmittedForm(policySeries, policyNumber, company, submittedForm);

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

    private String cleanDoubledPolicyNumber(String dirtyPolicy) {
        dirtyPolicy = dirtyPolicy.trim();

        Pattern pattern = Pattern.compile("^(.+?)\\s+\\1$");
        Matcher matcher = pattern.matcher(dirtyPolicy);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        String[] words = dirtyPolicy.split("\\s+");
        if(words.length > 1 && words.length % 2 == 0) {
            int halfIndex = words.length / 2;
            StringBuilder cleaned = new StringBuilder();
            for(int i = 0; i < halfIndex; i++) {
                cleaned.append(words[i]).append(" ");
            }
            return cleaned.toString().trim();
        }

        return dirtyPolicy;
    }
}
