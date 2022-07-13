package com.clususinterview.fxdealstask.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class FxDeal {

    @Column(name = "deal_unique_id", nullable = true)
    private String dealUniqueId;

    @Column(name = "source_file_name")
    private String sourceFileName;

    @Column(name = "from_currency_code", nullable = true)
    private String fromCurrencyCode;

    @Column(name = "to_currency_code", nullable = true)
    private String toCurrencyCode;

    @Column(name = "deal_timestamp", nullable = true)
    private String dealTimestamp;

    @Column(name = "deal_amount", nullable = true)
    private String dealAmount;

    public String getDealUniqueId() {
        return dealUniqueId;
    }

    public void setDealUniqueId(String dealUniqueId) {
        this.dealUniqueId = dealUniqueId;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public String getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(String dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }


}
