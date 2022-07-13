package com.clususinterview.fxdealstask.service;

import com.clususinterview.fxdealstask.dto.SubmittedFileReportDto;
import com.clususinterview.fxdealstask.model.SubmittedFileReport;
import com.clususinterview.fxdealstask.repository.SubmittedFileReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmittedFileReportServiceImpl implements SubmittedFileReportService {

    @Autowired
    SubmittedFileReportRepository submittedFileReportRepository;

    @Override
    public Optional<SubmittedFileReportDto> findBySourceFileName(String sourceFileName) {

        SubmittedFileReport submittedFileReport = submittedFileReportRepository.findSubmittedFileReportBySourceFileName(sourceFileName);

        if (submittedFileReport == null) {
            return Optional.empty();
        }

        SubmittedFileReportDto submittedFileReportDto = SubmittedFileReportDto.builder()
                .sourceFileName(sourceFileName)
                .validDealsCount(submittedFileReport.getValidDealsCount())
                .invalidDealsCount(submittedFileReport.getInvalidDealsCount())
                .importDuration(submittedFileReport.getImportDuration())
                .importDate(submittedFileReport.getImportDate())
                .build();

        return Optional.of(submittedFileReportDto);

    }
}
