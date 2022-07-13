package com.clususinterview.fxdealstask.repository;

import com.clususinterview.fxdealstask.model.InvalidFxDeal;

import java.util.List;

public interface InvalidFxDealsBatchInsertRepository {

    void batchInsert(List<InvalidFxDeal> invalidFxDeals);
}
