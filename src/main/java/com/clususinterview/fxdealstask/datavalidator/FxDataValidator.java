package com.clususinterview.fxdealstask.datavalidator;

import com.clususinterview.fxdealstask.dto.FxDealsDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Currency;

public class FxDataValidator {

    private FxDataValidator(){

    }

    private  static  final Logger LOGGER = LoggerFactory.getLogger(FxDataValidator.class);

    private static final DateTimeFormatter CSV_TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static boolean isValid(FxDealsDto fxDealsDto) {

        if (!hasAllFields(fxDealsDto)) {
            LOGGER.info("[isValid()] EmptyField: {}" , fxDealsDto);
            return false;
        }

        if (!hasValidCurrencyCode(fxDealsDto)) {
            LOGGER.info("[isValid()] Invalid currency code: {}", fxDealsDto);
            return false;
        }

        if (!hasValidDateFields(fxDealsDto)) {
            LOGGER.info("[isValid()] Invalid date: {}", fxDealsDto);
            return false;
        }

        if (!hasValidAmountFields(fxDealsDto)) {
            LOGGER.info("[isValid()]  InvalidAmount : {}" , fxDealsDto);
            return false;
        }

        return true;
    }

    private static boolean hasValidDateFields(FxDealsDto fxDealsDto) {

        try {
            LocalDateTime.parse(fxDealsDto.getDealTimestamp(), CSV_TIMESTAMP_FORMAT);
        } catch (DateTimeParseException exception) {
            return false;
        }

        return true;
    }

    private static boolean hasValidAmountFields(FxDealsDto fxDealsDto) {

        try {
            new BigDecimal(fxDealsDto.getDealAmount());
        } catch (NumberFormatException exception) {
            return false;
        }

        return true;
    }

    private static boolean hasAllFields(FxDealsDto fxDealsDto) {

        if (StringUtils.isBlank(fxDealsDto.getDealUniqueId())
                || StringUtils.isBlank(fxDealsDto.getFromCurrencyCode())
                || StringUtils.isBlank(fxDealsDto.getToCurrencyCode())
                || StringUtils.isBlank(fxDealsDto.getDealTimestamp())
                || StringUtils.isBlank(fxDealsDto.getDealAmount())) {
            return false;
        }

        return true;
    }

    private static boolean hasValidCurrencyCode(FxDealsDto fxDealsDto) {

        boolean hasValidToCurrencyCode = Currency.getAvailableCurrencies()
                .stream()
                .anyMatch(
                        currency -> currency.getCurrencyCode().equalsIgnoreCase(fxDealsDto.getToCurrencyCode())
                );

        boolean hasValidFromCurrencyCode = Currency.getAvailableCurrencies()
                .stream()
                .anyMatch(
                        currency -> currency.getCurrencyCode().equalsIgnoreCase(fxDealsDto.getFromCurrencyCode())
                );

        return hasValidFromCurrencyCode && hasValidToCurrencyCode;
    }
}
