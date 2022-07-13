package com.clususinterview.fxdealstask.util;

import com.clususinterview.fxdealstask.dto.FxDealsDto;
import com.clususinterview.fxdealstask.dto.SubmittedFileReportDto;
import com.clususinterview.fxdealstask.model.FxDeal;
import com.clususinterview.fxdealstask.model.InvalidFxDeal;
import com.clususinterview.fxdealstask.model.SubmittedFileReport;
import com.clususinterview.fxdealstask.model.ValidFxDeal;
import org.apache.commons.csv.CSVRecord;

public class DataObjectMapper {

    private  DataObjectMapper(){

    }

    public static SubmittedFileReport mapToSubmittedFileReport(SubmittedFileReportDto submittedFileReportDto) {

        SubmittedFileReport submittedFileReport = new SubmittedFileReport();

        submittedFileReport.setSourceFileName(submittedFileReportDto.getSourceFileName());
        submittedFileReport.setImportDate(submittedFileReportDto.getImportDate());
        submittedFileReport.setImportDuration(submittedFileReportDto.getImportDuration());
        submittedFileReport.setValidDealsCount(submittedFileReportDto.getValidDealsCount());
        submittedFileReport.setInvalidDealsCount(submittedFileReportDto.getInvalidDealsCount());

        return submittedFileReport;

    }

    public static InvalidFxDeal mapToInvalidFxDeals(FxDealsDto fxDealsDto) {

        InvalidFxDeal invalidFxDeal = new InvalidFxDeal();

        setProperties(fxDealsDto, invalidFxDeal);

        return invalidFxDeal;
    }

    public static ValidFxDeal mapToValidFxDeal(FxDealsDto fxDealsDto) {

        ValidFxDeal validFxDeal = new ValidFxDeal();

        setProperties(fxDealsDto, validFxDeal);

        return validFxDeal;
    }

    public static FxDealsDto mapToFxDealsDto(CSVRecord csvRecord, String originalFileName) {

        return FxDealsDto.builder()
                .sourceFileName(originalFileName)
                .dealUniqueId(csvRecord.get(FxDealsDto.DEAL_ID))
                .fromCurrencyCode(csvRecord.get(FxDealsDto.FROM_CURRENCY))
                .toCurrencyCode(csvRecord.get(FxDealsDto.TO_CURRENCY))
                .dealTimestamp(csvRecord.get(FxDealsDto.TIMESTAMP))
                .dealAmount(csvRecord.get(FxDealsDto.AMOUNT))
                .build();

    }


    private static void setProperties(FxDealsDto fxDealsDto, FxDeal fxDeal) {
        fxDeal.setSourceFileName(fxDealsDto.getSourceFileName());
        fxDeal.setDealUniqueId(fxDealsDto.getDealUniqueId());
        fxDeal.setFromCurrencyCode(fxDealsDto.getFromCurrencyCode());
        fxDeal.setToCurrencyCode(fxDealsDto.getToCurrencyCode());
        fxDeal.setDealTimestamp(fxDealsDto.getDealTimestamp());
        fxDeal.setDealAmount(fxDealsDto.getDealAmount());
    }
}
