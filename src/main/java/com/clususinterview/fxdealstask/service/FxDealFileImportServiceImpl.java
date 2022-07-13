package com.clususinterview.fxdealstask.service;

import com.clususinterview.fxdealstask.datavalidator.FileValidator;
import com.clususinterview.fxdealstask.datavalidator.FxDataValidator;
import com.clususinterview.fxdealstask.dto.FxDealsDto;
import com.clususinterview.fxdealstask.dto.SubmittedFileReportDto;
import com.clususinterview.fxdealstask.exception.FileImportException;
import com.clususinterview.fxdealstask.model.InvalidFxDeal;
import com.clususinterview.fxdealstask.model.SubmittedFileReport;
import com.clususinterview.fxdealstask.model.ValidFxDeal;
import com.clususinterview.fxdealstask.util.DataObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FxDealFileImportServiceImpl implements FxDealFileImportService {

    @Autowired
    DbTransactionServiceImpl dbTransactionService;

    @Autowired
    SubmittedFileReportServiceImpl submittedFileReportService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FxDealFileImportServiceImpl.class);

    @Override
    public void uploadFile(MultipartFile multipartFile) throws FileImportException {

        String originalFilename = multipartFile.getOriginalFilename();

        if (!isFileExtensionFormatValid(originalFilename)) {
            throw new FileImportException("Invalid file extension : " + originalFilename);
        }

        final long startTime = Instant.now().toEpochMilli();

        List<ValidFxDeal> validFxDeals = new ArrayList<>();
        List<InvalidFxDeal> invalidFxDeals = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {

            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.RFC4180.withHeader());

            List<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords
            ) {

                FxDealsDto fxDealsDto = DataObjectMapper.mapToFxDealsDto(csvRecord, originalFilename);

                if (FxDataValidator.isValid(fxDealsDto)) {
                    validFxDeals.add(DataObjectMapper.mapToValidFxDeal(fxDealsDto));
                } else {
                    invalidFxDeals.add(DataObjectMapper.mapToInvalidFxDeals(fxDealsDto));
                }
            }

            dbTransactionService.saveAllValidFxDeals(validFxDeals);
            dbTransactionService.saveAllInvalidFxDeals(invalidFxDeals);

            LOGGER.info("[uploadFxFile()] Creating SubmittedFileReport record for the  file {}...", originalFilename);

            long duration = Instant.now().toEpochMilli() - startTime;

            SubmittedFileReport submittedFileReport = DataObjectMapper.mapToSubmittedFileReport(
                    getSubmittedFileReportDto(originalFilename, startTime, validFxDeals.size(), invalidFxDeals.size(), duration)
            );

            dbTransactionService.saveSubmittedFileReport(submittedFileReport);

        } catch (Exception e) {
            throw new FileImportException("[uploadFile()] error in importing the file " + multipartFile.getOriginalFilename(), e);
        }
    }

    public SubmittedFileReportDto getSubmittedFileReportDto(String orginalFileName, long startTime, Integer validFxDealsCount, Integer invalidFxDealsCount, long duration) {

        return SubmittedFileReportDto.builder()
                .sourceFileName(orginalFileName)
                .importDate(new Date(startTime))
                .validDealsCount(validFxDealsCount)
                .invalidDealsCount(invalidFxDealsCount)
                .importDuration((int)TimeUnit.MILLISECONDS.toSeconds(duration))
                .build();
    }

    @Override
    public boolean isFileAlreadyImported(String sourceFileName) {
        return submittedFileReportService.findBySourceFileName(sourceFileName).isPresent();
    }

    public boolean isFileExtensionFormatValid(String filename) {
        return FileValidator.validateFileExtension(filename);
    }
}
