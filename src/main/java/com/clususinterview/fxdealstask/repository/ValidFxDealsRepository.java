package com.clususinterview.fxdealstask.repository;

import com.clususinterview.fxdealstask.model.ValidFxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidFxDealsRepository extends JpaRepository<ValidFxDeal, String> {
}
