package com.clususinterview.fxdealstask.service;

import com.clususinterview.fxdealstask.model.InvalidFxDeal;
import com.clususinterview.fxdealstask.model.SubmittedFileReport;
import com.clususinterview.fxdealstask.model.ValidFxDeal;
import com.clususinterview.fxdealstask.repository.InvalidFxDealsBatchInsertRepositoryImpl;
import com.clususinterview.fxdealstask.repository.SubmittedFileReportRepository;
import com.clususinterview.fxdealstask.repository.ValidFxDealsBatchInsertRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbTransactionServiceImplTest {


    public static final String DATA_CSV = "data.csv";
    @Mock
    SubmittedFileReportRepository submittedFileReportRepository;

    @Mock
    ValidFxDealsBatchInsertRepositoryImpl validFxDealsBatchInsertRepository;

    @Mock
    InvalidFxDealsBatchInsertRepositoryImpl invalidFxDealsBatchInsertRepository;

    @InjectMocks
    DbTransactionServiceImpl dbTransactionService = new DbTransactionServiceImpl();


    @Test
     void test_SaveSubmittedFileReportWithNoError() {

        SubmittedFileReport submittedFileReport = mock(SubmittedFileReport.class);

        submittedFileReport.setSourceFileName(DATA_CSV);
        submittedFileReport.setInvalidDealsCount(100);
        submittedFileReport.setValidDealsCount(900);
        submittedFileReport.setImportDuration(10);
        submittedFileReport.setImportDate(new Date());

        dbTransactionService.saveSubmittedFileReport(submittedFileReport);

        verify(submittedFileReportRepository, atLeastOnce()).save(submittedFileReport);
    }

    @Test
     void test_SaveAllValidFxDealsWithNoError() {
        List<ValidFxDeal> validFxDeals = new ArrayList<>();

        dbTransactionService.saveAllValidFxDeals(validFxDeals);

        verify(validFxDealsBatchInsertRepository, atLeastOnce()).batchInsert(validFxDeals);
    }


    @Test
     void test_SaveAllInvalidFxDealsWithNoError() {
        List<InvalidFxDeal> invalidFxDeals = new ArrayList<>();

        dbTransactionService.saveAllInvalidFxDeals(invalidFxDeals);

        verify(invalidFxDealsBatchInsertRepository, atLeastOnce()).batchInsert(invalidFxDeals);
    }


}