package com.clususinterview.fxdealstask.service;

import com.clususinterview.fxdealstask.model.InvalidFxDeal;
import com.clususinterview.fxdealstask.model.SubmittedFileReport;
import com.clususinterview.fxdealstask.model.ValidFxDeal;

import java.util.List;

public interface DbTransactionService {

    public void saveSubmittedFileReport(SubmittedFileReport submittedFileReport);

    public void saveAllValidFxDeals(List<ValidFxDeal> validFxDeals);

    public void saveAllInvalidFxDeals(List<InvalidFxDeal> invalidFxDeals);
}
