package com.clususinterview.fxdealstask.service;


import com.clususinterview.fxdealstask.model.SubmittedFileReport;
import com.clususinterview.fxdealstask.repository.SubmittedFileReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubmittedFileReportServiceImplTest {

    public static final String DUPLICATE_CSV = "duplicate.csv";
    public static final String NEW_FILE_CSV = "newFile.csv";

    @Mock
    SubmittedFileReportRepository submittedFileReportRepository;

    @InjectMocks
    SubmittedFileReportServiceImpl submittedFileReportServiceImpl = new SubmittedFileReportServiceImpl();

    @Test
    void isFileAlreadyImported_WhenNew_ThenReturnEmpty() {

        when(submittedFileReportRepository.findSubmittedFileReportBySourceFileName(NEW_FILE_CSV))
                .thenReturn(null);

        assertFalse(submittedFileReportServiceImpl.findBySourceFileName(NEW_FILE_CSV).isPresent());

    }

    @Test
    void isFileAlreadyImported_WhenDuplicate_ThenReturnSubmittedFileReport() {


        SubmittedFileReport submittedFileReport = mock(SubmittedFileReport.class);

        submittedFileReport.setSourceFileName(DUPLICATE_CSV);
        submittedFileReport.setInvalidDealsCount(100);
        submittedFileReport.setValidDealsCount(900);
        submittedFileReport.setImportDuration(10);
        submittedFileReport.setImportDate(new Date());

        when(submittedFileReportRepository.findSubmittedFileReportBySourceFileName(DUPLICATE_CSV))
                .thenReturn(submittedFileReport);

        assertEquals("duplicate.csv", submittedFileReportServiceImpl.findBySourceFileName(DUPLICATE_CSV).get().getSourceFileName());
    }

}