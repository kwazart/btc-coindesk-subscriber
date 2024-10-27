package com.polozov.btccoindesksubscriber.controller;

import com.polozov.btccoindesksubscriber.entity.ValuteDataRecord;
import com.polozov.btccoindesksubscriber.service.ValuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rates")
public class RateController {

    private final ValuteService valuteService;

    @GetMapping
    public List<ValuteDataRecord> getAll() {
        return valuteService.getAll();
    }

    @GetMapping("/hour")
    public List<ValuteDataRecord> getAllSinceStartOfHour() {
        return valuteService.getAllSinceStartOfHour(null);
    }
}
