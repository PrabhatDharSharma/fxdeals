package com.clususinterview.fxdealstask.service;

import com.clususinterview.fxdealstask.dto.SubmittedFileReportDto;

import java.util.Optional;

public interface SubmittedFileReportService {

    Optional<SubmittedFileReportDto> findBySourceFileName(String sourceFileName);
}
