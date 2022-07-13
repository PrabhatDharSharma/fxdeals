package com.clususinterview.fxdealstask.service;

import com.clususinterview.fxdealstask.dto.SubmittedFileReportDto;
import com.clususinterview.fxdealstask.exception.FileImportException;
import com.clususinterview.fxdealstask.model.SubmittedFileReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FxDealFileImportServiceImplTest {

    public static final String DATA_CSV = "data.csv";
    public static final String DUPLICATE_CSV = "duplicate.csv";

    private static final String CONTENT = "DEAL_ID,FROM_CURRENCY,TO_CURRENCY,TIMESTAMP,AMOUNT\n" +
            "wmwqwezb-9936-4j41-c46q-92220j29rlrf,EUR,LAK,2022-03-23 09:18:24,5.84\n" +
            "znluejzy-6586-4x19-w14t-26612w39bbvu,IDR,LTL,2021-08-23 12:01:49,75.99\n" +
            "zndaafoa-0733-7j49-k63z-25679r70ggik,IRR,PLN,2022-05-12 04:18:58,38.45";


    @Mock
    SubmittedFileReportServiceImpl submittedFileReportService;

    @Mock
    DbTransactionServiceImpl dbTransactionService;

    @InjectMocks
    FxDealFileImportServiceImpl fxDealFileImportService = new FxDealFileImportServiceImpl();

    @Test
    void uploadFile() throws FileImportException {

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "fxdata.csv",
                MediaType.TEXT_PLAIN_VALUE,
                CONTENT.getBytes()
        );


        doNothing().when(dbTransactionService)
                .saveAllValidFxDeals(anyList());

        doNothing().when(dbTransactionService)
                .saveAllInvalidFxDeals(anyList());

        doNothing().when(dbTransactionService)
                .saveSubmittedFileReport(any(SubmittedFileReport.class));


        fxDealFileImportService.uploadFile(file);

        verify(dbTransactionService, atLeastOnce()).saveSubmittedFileReport(any(SubmittedFileReport.class));

        verify(dbTransactionService, atLeastOnce()).saveAllValidFxDeals(anyList());

        verify(dbTransactionService, atLeastOnce()).saveAllInvalidFxDeals(anyList());

    }

    @Test
    void uploadFileWithException() throws FileImportException {

        MockMultipartFile mockMultipartFile
                = new MockMultipartFile(
                "file",
                "fxdata.html",
                MediaType.TEXT_HTML_VALUE,
                "<head><h1>Title</h1></head>".getBytes()
        );

        Exception exception = assertThrows(FileImportException.class, () -> {
            fxDealFileImportService.uploadFile(mockMultipartFile);
        });

        assertEquals("Invalid file extension : fxdata.html", exception.getMessage());

    }

    @Test
    void isFileAlreadyImported() {
        when(submittedFileReportService.findBySourceFileName(DATA_CSV)).thenReturn(
                Optional.of(
                        SubmittedFileReportDto.builder().sourceFileName(DATA_CSV).build()
                )
        );
        assertTrue(fxDealFileImportService.isFileAlreadyImported("data.csv"));


        when(submittedFileReportService.findBySourceFileName(DUPLICATE_CSV)).thenReturn(Optional.empty());

        assertFalse(fxDealFileImportService.isFileAlreadyImported("duplicate.csv"));
    }


    @Test
    void getSubmittedFileReportDto() {

        String orginalFileName = "data.csv";
        long startTime = 1349333576093L;
        Integer validFxDealsCount = 200;
        Integer invalidFxDealsCount = 2;
        long duration = 3;

        assertEquals(200, fxDealFileImportService.getSubmittedFileReportDto(
                orginalFileName, startTime, validFxDealsCount, invalidFxDealsCount, duration
        ).getValidDealsCount());

    }
}