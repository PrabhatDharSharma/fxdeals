package com.clususinterview.fxdealstask.repository;

import com.clususinterview.fxdealstask.model.SubmittedFileReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmittedFileReportRepository extends JpaRepository<SubmittedFileReport,String> {

    SubmittedFileReport findSubmittedFileReportBySourceFileName(String sourceFileName);
}
