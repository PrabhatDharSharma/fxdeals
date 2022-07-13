package com.clususinterview.fxdealstask.datavalidator;

import com.clususinterview.fxdealstask.dto.FxDealsDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FxDataValidatorTest {

    @Test
    void when_Deal_Id_Blank_Then_False() {
        FxDealsDto fxDealsDto = FxDealsDto.builder()
                .dealUniqueId("")
                .fromCurrencyCode("NPR")
                .toCurrencyCode("USD")
                .dealTimestamp("2019-04-23 11:38:23")
                .dealAmount("5")
                .build();

        assertFalse(FxDataValidator.isValid(fxDealsDto));

    }

    @Test
    void when_From_Currency_Code_Blank_Then_False() {
        FxDealsDto fxDealsDto = FxDealsDto.builder()
                .dealUniqueId("vghyrxmt-1808-0u12-g51j-40563n50gmbx")
                .fromCurrencyCode("")
                .toCurrencyCode("USD")
                .dealTimestamp("2019-04-23 11:38:23")
                .dealAmount("5")
                .build();

        assertFalse(FxDataValidator.isValid(fxDealsDto));

    }

    @Test
    void when_To_Currency_Code_Blank_Then_False() {
        FxDealsDto fxDealsDto = FxDealsDto.builder()
                .dealUniqueId("vghyrxmt-1808-0u12-g51j-40563n50gmbx")
                .fromCurrencyCode("JOD")
                .toCurrencyCode("")
                .dealTimestamp("2019-04-23 11:38:23")
                .dealAmount("5")
                .build();

        assertFalse(FxDataValidator.isValid(fxDealsDto));

    }

    @Test
    void when_Timestamp_Blank_Then_False() {
        FxDealsDto fxDealsDto = FxDealsDto.builder()
                .dealUniqueId("vghyrxmt-1808-0u12-g51j-40563n50gmbx")
                .fromCurrencyCode("JOD")
                .toCurrencyCode("USD")
                .dealTimestamp("")
                .dealAmount("5")
                .build();

        assertFalse(FxDataValidator.isValid(fxDealsDto));

    }

    @Test
    void when_Deal_Amount_Blank_Then_False() {
        FxDealsDto fxDealsDto = FxDealsDto.builder()
                .dealUniqueId("vghyrxmt-1808-0u12-g51j-40563n50gmbx")
                .fromCurrencyCode("JOD")
                .toCurrencyCode("USD")
                .dealTimestamp("2019-04-23 11:38:23")
                .dealAmount("")
                .build();

        assertFalse(FxDataValidator.isValid(fxDealsDto));

    }

    @Test
    void when_Timestamp_Wrong_Format_Return_False() {
        FxDealsDto fxDealsDto = FxDealsDto.builder()
                .dealUniqueId("vghyrxmt-1808-0u12-g51j-40563n50gmbx")
                .fromCurrencyCode("JOD")
                .toCurrencyCode("USD")
                .dealTimestamp("2019-04-23")
                .dealAmount("30.50")
                .build();

        assertFalse(FxDataValidator.isValid(fxDealsDto));

    }

    @Test
    void when_Deal_Amount_Wrong_Format_Then_False() {
        FxDealsDto fxDealsDto = FxDealsDto.builder()
                .dealUniqueId("vghyrxmt-1808-0u12-g51j-40563n50gmbx")
                .fromCurrencyCode("JOD")
                .toCurrencyCode("USD")
                .dealTimestamp("2019-04-23 11:38:23")
                .dealAmount("hundred")
                .build();

        assertFalse(FxDataValidator.isValid(fxDealsDto));

    }

    @Test
    void when_Invalid_Currency_Code_Then_False() {
        FxDealsDto fxDealsDto = FxDealsDto.builder()
                .dealUniqueId("vghyrxmt-1808-0u12-g51j-40563n50gmbx")
                .fromCurrencyCode("UNKNOWN")
                .toCurrencyCode("USD")
                .dealTimestamp("2019-04-23 11:38:23")
                .dealAmount("30.50")
                .build();

        assertFalse(FxDataValidator.isValid(fxDealsDto));

    }

    @Test
    void when_All_Data_Valid_Then_true() {
        FxDealsDto fxDealsDto = FxDealsDto.builder()
                .dealUniqueId("vghyrxmt-1808-0u12-g51j-40563n50gmbx")
                .fromCurrencyCode("JOD")
                .toCurrencyCode("USD")
                .dealTimestamp("2019-04-23 11:38:23")
                .dealAmount("30.50")
                .build();

        assertTrue(FxDataValidator.isValid(fxDealsDto));

    }

}