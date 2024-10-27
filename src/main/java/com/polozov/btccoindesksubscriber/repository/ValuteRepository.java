package com.polozov.btccoindesksubscriber.repository;

import com.polozov.btccoindesksubscriber.entity.ValuteDataRecord;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ValuteRepository extends JpaRepository<ValuteDataRecord, UUID> {

    List<ValuteDataRecord> findAll();

    @Query("select v from ValuteDataRecord v where v.createAt >= :start")
    List<ValuteDataRecord> findAllAndCreateAtAfterOrderByCreateAt(@Param("start")LocalDateTime start);
}
