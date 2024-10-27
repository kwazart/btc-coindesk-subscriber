package com.polozov.btccoindesksubscriber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Сводная информация по активу.
 */
@Data
public class ValuteDataResponse {

    /**
     * Блок, описывающий время.
     */
    @JsonIgnore
    private Time time;

    /**
     * Описание записи.
     */
    private String disclaimer;

    /**
     * Полное наименование основного актива.
     */
    private String chartName;

    /**
     * Блоки с валютами в отношении с основным активом.
     */
    @JsonProperty("bpi")
    private BPI bpi;


    @Data
    public static class Time {
        private Date updated;
        private Date updatedISO;
        private Date updateduk;
    }

    /**
     * Блок одной валюты по отношению к основному активу.
     */
    @Data
    public static class Valute {

        /**
         * Код валюты.
         */
        private String code;

        /**
         * Код из таблицы Unicode.
         */
        private String symbol;

        /**
         * Значение основного актива в текущей валюте в строковом виде.
         */
        private String rate;

        /**
         * Описание текущей валюты.
         */
        private String description;

        /**
         * Значение основного актива в текущей валюте в числовом виде.
         */
        @JsonProperty("rate_float")
        private float rateFloat;
    }

    @Data
    public static class BPI {
        /**
         * Доллар.
         */
        @JsonProperty("USD")
        private Valute usd;

        /**
         * Британский фунт.
         */
        @JsonProperty("GBP")
        private Valute gbp;

        /**
         * Евро.
         */
        @JsonProperty("EUR")
        private Valute eur;
    }
}
