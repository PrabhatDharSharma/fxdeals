package com.clususinterview.fxdealstask.repository;

import com.clususinterview.fxdealstask.model.ValidFxDeal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ValidFxDealsBatchInsertRepositoryImpl implements ValidFxDealsBatchInsertRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidFxDealsBatchInsertRepositoryImpl.class);

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Autowired
    ValidFxDealsRepository validFxDealsRepository;

    @Override
    public void batchInsert(List<ValidFxDeal> validFxDeals) {

        LOGGER.info("Batch inserting the invalid fx deals...");

        List<ValidFxDeal> validFxDealsToSave = new ArrayList<>();

        for (int i = 0; i < validFxDeals.size(); i++) {
            ValidFxDeal validFxDeal = validFxDeals.get(i);

            validFxDealsToSave.add(validFxDeal);

            if (i % batchSize == 0 && i > 0) {
                validFxDealsRepository.saveAll(validFxDealsToSave);
                validFxDealsToSave.clear();
            }
        }

        if (validFxDealsToSave.isEmpty()) {
            validFxDealsRepository.saveAll(validFxDealsToSave);
            validFxDealsToSave.clear();
        }
        LOGGER.info("Batch inserting the invalid fx deals complete!");
    }
}
