package com.polozov.btccoindesksubscriber.service;

import com.polozov.btccoindesksubscriber.apiClient.ExchangeApiClient;
import com.polozov.btccoindesksubscriber.entity.ValuteDataRecord;
import com.polozov.btccoindesksubscriber.model.ValuteDataResponse;
import com.polozov.btccoindesksubscriber.repository.ValuteRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ValuteService {

    private final ExchangeApiClient exchangeApiclient;
    private final ValuteRepository valuteRepository;
    private final AtomicInteger btcInUsdPrice;
    private final AtomicInteger btcInGpbPrice;
    private final AtomicInteger btcInEurPrice;


    public ValuteService(ExchangeApiClient exchangeApiclient,
                         ValuteRepository valuteRepository,
                         MeterRegistry meterRegistry) {
        this.exchangeApiclient = exchangeApiclient;
        this.valuteRepository = valuteRepository;
        btcInUsdPrice = new AtomicInteger();
        btcInGpbPrice = new AtomicInteger();
        btcInEurPrice = new AtomicInteger();
        meterRegistry.gauge("btcInUsd", btcInUsdPrice);
        meterRegistry.gauge("btcInGbp", btcInGpbPrice);
        meterRegistry.gauge("btcInEur", btcInEurPrice);
    }

    public void getRate() {
        ValuteDataResponse data = exchangeApiclient.getData();

        if (data == null) {
            throw new RuntimeException("Data is empty");
        }
        var rateFloatUsd = (int) data.getBpi().getUsd().getRateFloat();
        var rateFloatGpb = (int) data.getBpi().getGbp().getRateFloat();
        var rateFloatEur = (int) data.getBpi().getEur().getRateFloat();

        btcInUsdPrice.set(rateFloatUsd);
        btcInGpbPrice.set(rateFloatGpb);
        btcInEurPrice.set(rateFloatEur);

        ValuteDataRecord valuteData = new ValuteDataRecord();
        valuteData.setCoinName(data.getChartName());
        valuteData.setUsdPrice(rateFloatUsd);
        valuteData.setEurPrice(rateFloatEur);
        valuteData.setGbpPrice(rateFloatGpb);

        valuteRepository.saveAndFlush(valuteData);
    }

    public List<ValuteDataRecord> getAll() {
        return valuteRepository.findAll();
    }

    public List<ValuteDataRecord> getAllSinceStartOfHour(LocalDateTime startDateTime) {
        var start = startDateTime != null
                ? startDateTime
                : LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        return valuteRepository.findAllAndCreateAtAfterOrderByCreateAt(start);
    }
}
