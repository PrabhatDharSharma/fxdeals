package com.clususinterview.fxdealstask.service;

import com.clususinterview.fxdealstask.model.InvalidFxDeal;
import com.clususinterview.fxdealstask.model.SubmittedFileReport;
import com.clususinterview.fxdealstask.model.ValidFxDeal;
import com.clususinterview.fxdealstask.repository.InvalidFxDealsBatchInsertRepositoryImpl;
import com.clususinterview.fxdealstask.repository.SubmittedFileReportRepository;
import com.clususinterview.fxdealstask.repository.ValidFxDealsBatchInsertRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbTransactionServiceImpl implements DbTransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbTransactionServiceImpl.class);

    @Autowired
    SubmittedFileReportRepository submittedFileReportRepository;

    @Autowired
    ValidFxDealsBatchInsertRepositoryImpl validFxDealsBatchInsertRepository;

    @Autowired
    InvalidFxDealsBatchInsertRepositoryImpl invalidFxDealsBatchInsertRepository;

    @Override
    public void saveSubmittedFileReport(SubmittedFileReport submittedFileReport) {

        LOGGER.info("Saving the submitted file report..");

        submittedFileReportRepository.save(submittedFileReport);

    }

    @Override
    public void saveAllValidFxDeals(List<ValidFxDeal> validFxDeals) {

        validFxDealsBatchInsertRepository.batchInsert(validFxDeals);

    }

    @Override
    public void saveAllInvalidFxDeals(List<InvalidFxDeal> invalidFxDeals) {

        invalidFxDealsBatchInsertRepository.batchInsert(invalidFxDeals);

    }
}
