package com.clususinterview.fxdealstask.repository;

import com.clususinterview.fxdealstask.model.ValidFxDeal;

import java.util.List;

public interface ValidFxDealsBatchInsertRepository {

    void batchInsert(List<ValidFxDeal> validFxDeals);
}
