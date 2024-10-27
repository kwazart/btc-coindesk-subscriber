package com.polozov.btccoindesksubscriber.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ValuteDataRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String coinName;
    private LocalDateTime createAt = LocalDateTime.now();
    private double usdPrice;
    private double gbpPrice;
    private double eurPrice;

}
