package com.clususinterview.fxdealstask.repository;

import com.clususinterview.fxdealstask.model.InvalidFxDeal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvalidFxDealsBatchInsertRepositoryImpl implements InvalidFxDealsBatchInsertRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidFxDealsBatchInsertRepositoryImpl.class);

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Autowired
    InvalidFxDealsRepository invalidFxDealsRepository;

    @Override
    public void batchInsert(List<InvalidFxDeal> invalidFxDeals) {

        LOGGER.info("Batch inserting the invalid fx deals...");

        List<InvalidFxDeal> invalidFxDealsToSave = new ArrayList<>();

        for (int i = 0; i < invalidFxDeals.size(); i++) {
            InvalidFxDeal invalidFxDeal = invalidFxDeals.get(i);

            invalidFxDealsToSave.add(invalidFxDeal);

            if (i % batchSize == 0 && i > 0) {
                invalidFxDealsRepository.saveAll(invalidFxDealsToSave);
                invalidFxDealsToSave.clear();
            }
        }

        if (invalidFxDealsToSave.isEmpty()) {
            invalidFxDealsRepository.saveAll(invalidFxDealsToSave);
            invalidFxDealsToSave.clear();
        }

        LOGGER.info("Batch inserting the invalid fx deals complete!");

    }
}
