package com.clususinterview.fxdealstask.controller;

import com.clususinterview.fxdealstask.exceptionhandler.GlobalExceptionHandler;
import com.clususinterview.fxdealstask.service.DbTransactionServiceImpl;
import com.clususinterview.fxdealstask.service.FxDealFileImportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class FxDealsFileImportControllerTest {

    private static final String CONTENT = "DEAL_ID,FROM_CURRENCY,TO_CURRENCY,TIMESTAMP,AMOUNT\n" +
            "wmwqwezb-9936-4j41-c46q-92220j29rlrf,EUR,LAK,2022-03-23 09:18:24,5.84\n" +
            "znluejzy-6586-4x19-w14t-26612w39bbvu,IDR,LTL,2021-08-23 12:01:49,75.99\n" +
            "zndaafoa-0733-7j49-k63z-25679r70ggik,IRR,PLN,2022-05-12 04:18:58,38.45";

    private MockMvc mockMvc;

    @Mock
    FxDealFileImportServiceImpl fxDealFileImportService;


    @Mock
    DbTransactionServiceImpl dbTransactionService;

    @InjectMocks
    private FxDealsFileImportController fxDealsFileImportController;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(fxDealsFileImportController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void when_ValidFxFile_Uploaded_thenVerifyStatus()
            throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "fxdata.csv",
                MediaType.TEXT_PLAIN_VALUE,
                CONTENT.getBytes()
        );

        doNothing().when(fxDealFileImportService).uploadFile(file);

        String message = "Successfully imported " + file.getOriginalFilename();
        mockMvc.perform(multipart("/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string(message));
    }


    @Test
    void when_Duplicate_FxFile_Uploaded_thenVerifyStatus()
            throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "fxdata.csv",
                MediaType.TEXT_PLAIN_VALUE,
                CONTENT.getBytes()
        );

        doNothing().when(fxDealFileImportService).uploadFile(file);

        when(fxDealFileImportService.isFileAlreadyImported(file.getOriginalFilename())).thenReturn(true);

        String message = "Duplicate file received!";
        mockMvc.perform(multipart("/upload").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(message));
    }

}