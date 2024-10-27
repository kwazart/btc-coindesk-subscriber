package com.polozov.btccoindesksubscriber.apiClient;

import com.polozov.btccoindesksubscriber.model.ValuteDataResponse;
import io.micrometer.core.annotation.Timed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(name = "coindesk", url = "${coindesk.url}")
public interface ExchangeApiClient {

    @Timed("gettingRateFromCoinDesk") // время получения данных от coindesk
    @GetMapping
    ValuteDataResponse getData();

}
