package com.polozov.btccoindesksubscriber.repository;

import com.polozov.btccoindesksubscriber.entity.ValuteDataRecord;
import feign.Param;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ValuteRepository extends JpaRepository<ValuteDataRecord, UUID> {

    @Timed("getting_all_rates") // время получения данных (всех) из БД
    List<ValuteDataRecord> findAll();

    @Timed("getting-all-rates-hour") // время получения данных с начала часа из БД
    @Query("select v from ValuteDataRecord v where v.createAt >= :start")
    List<ValuteDataRecord> findAllAndCreateAtAfterOrderByCreateAt(@Param("start")LocalDateTime start);
}
