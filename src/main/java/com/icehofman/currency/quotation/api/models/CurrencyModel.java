package com.icehofman.currency.quotation.api.models;

import java.math.BigDecimal;

public class CurrencyModel {

    private final String SEPARATOR = ";";
    private String name;
    private BigDecimal sellingRate;

    public CurrencyModel(String line) throws IllegalArgumentException {
        try {
            String[] data = line.split(SEPARATOR);
            this.name = data[3];
            this.sellingRate = new BigDecimal(data[5].replace(",", "."));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSellingRate() {
        return sellingRate;
    }
}
