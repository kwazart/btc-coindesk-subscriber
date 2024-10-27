package com.polozov.btccoindesksubscriber.scheduler;

import com.polozov.btccoindesksubscriber.service.ValuteService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RateBtcScheduler {

    private final ValuteService valuteService;

    @Scheduled(fixedDelay = 20_000)
    public void updateRate() {
        valuteService.getRate();
    }
}