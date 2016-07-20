package com.icehofman.currency.quotation.api.services;

import com.icehofman.currency.quotation.api.exceptions.NoExchangeRateForThisDateException;
import com.icehofman.currency.quotation.api.models.Currency;

import java.util.HashMap;
import java.util.List;

public class CashService {

    private static CashService cashService;

    private HashMap<String, List<Currency>> cashCurrencies;
    private ExchangeRateService exchangeRateService;

    public CashService(ExchangeRateService exchangeRateService) {
        this.cashCurrencies = new HashMap<String, List<Currency>>();
        this.exchangeRateService = exchangeRateService;
    }

    public static CashService getInstance(ExchangeRateService exchangeRateService) {
        if (cashService == null) {
            cashService = new CashService(exchangeRateService);
        }
        return cashService;
    }

    public List<Currency> getCurrencies(String quotation) throws NoExchangeRateForThisDateException {
        List<Currency> currencies = this.getCurrenciesOfMap(quotation);
        if (currencies == null) {
            currencies = exchangeRateService.getCurrencies(quotation);
            if (currencies == null) {
                throw new NoExchangeRateForThisDateException();
            }
            this.addCurrencies(quotation, currencies);
        }
        return currencies;
    }

    private void addCurrencies(String key, List<Currency> currencies) {
        cashCurrencies.put(key, currencies);
    }

    private List<Currency> getCurrenciesOfMap(String key) {
        return cashCurrencies.get(key);
    }
}
