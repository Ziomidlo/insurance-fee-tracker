package com.example.InsuranceFeeTracker.service;

import com.example.InsuranceFeeTracker.model.FeeStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class FeeStatementParser {

    private static final List<String> KNOWN_COMPANIES = List.of("COMPENSA" , "HESTIA MTU", "LINK4",
            "PZU", "WARTA", "WIENER", "INTERRISK", "ALLIANZ", "BALCIA", "GENERALI", "IHESTIA", "INTERPOLSKA",
    "LINK4", "TUZ", "UNIQA", "TUWTUW");

    private final PolicyProcessingService policyProcessingService;


    public FeeStatementParser(PolicyProcessingService policyProcessingService) {
        this.policyProcessingService = policyProcessingService;
    }

    public void parseExtractedText(String rawPdfText) {
        String regex = "([\\s\\S]*?)\\b([A-Za-z]*)(\\d+)\\b\\s+(\\d{4}-\\d{2}-\\d{2})\\s+(\\d+)\\s+([\\s\\S]*?)" +
                "\\s+(\\d+,\\d{2})\\s+(\\d+,\\d{1,2})\\s+(\\d+,\\d{2})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawPdfText);

        while(matcher.find()) {
            String insuranceCompanyAndDetailsStr = matcher.group(1);
            String policySeriesStr = matcher.group(2);
            String policyNumberStr = matcher.group(3);
            String conclusionDateStr = matcher.group(4);
            String installmentStr = matcher.group(5);
            String productAndRiskStr = matcher.group(6);
            String collectionStr = matcher.group(7);
            String rateStr = matcher.group(8);
            String commissionAmountStr = matcher.group(9);

            String company = "UNKNOWN";
            for(String knownCompany : KNOWN_COMPANIES) {
                if(insuranceCompanyAndDetailsStr.contains(knownCompany)) {
                    company = knownCompany;
                    break;
                }
            }

            productAndRiskStr = productAndRiskStr.replace("\n", " ").trim();

            String fullPolicyNumber = policySeriesStr + policyNumberStr;

            LocalDate conclusionDate = LocalDate.parse(conclusionDateStr);
            int installment = Integer.parseInt(installmentStr);
            BigDecimal collection = new BigDecimal(collectionStr.replace("," , "."));
            BigDecimal rate = new BigDecimal(rateStr.replace("," , "."));
            BigDecimal commissionAmount = new BigDecimal(commissionAmountStr.replace("," , "."));


            log.info("Found a data from fee statement -> Company {}, policySeries {}, policyNumber {}, fullPolicyNumber {}, conclusionDate {}, installment {}," +
                    "collection {}, rate {}, commissionAmount {}", company, policySeriesStr, policyNumberStr, fullPolicyNumber, conclusionDate, installment,
                    collection, rate, commissionAmount);


            FeeStatement feeStatement = FeeStatement.builder()
                    .office("KLIMONTÓW")
                    .createdDate(conclusionDate)
                    .installment(installment)
                    .product("")
                    .risk("")
                    .collection(collection)
                    .rate(rate)
                    .commissionAmount(commissionAmount)
                    .build();

            policyProcessingService.processFeeStatement(policySeriesStr, policyNumberStr, company, feeStatement);


        }
    }
}
