package com.clususinterview.fxdealstask.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FxDealsDto {

    public static final String DEAL_ID = "DEAL_ID";
    public static final String FROM_CURRENCY = "FROM_CURRENCY";
    public static final String TO_CURRENCY = "TO_CURRENCY";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String AMOUNT = "AMOUNT";

    private String dealUniqueId;

    private String sourceFileName;

    private String fromCurrencyCode;

    private String toCurrencyCode;

    private String dealTimestamp;

    private String dealAmount;
}
