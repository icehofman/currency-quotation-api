package com.icehofman.currency.quotation.api.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.icehofman.currency.quotation.api.exceptions.NoExchangeRateForThisDateException;
import com.icehofman.currency.quotation.api.models.Currency;
import com.icehofman.currency.quotation.api.models.Period;

import java.util.Date;
import java.util.List;

public class ExchangeRateServiceTest {

    private ExchangeRateService exchangeRateService;

    @Before
    public void setUp() throws Exception {
        exchangeRateService = new ExchangeRateService();
    }

    @Test
    public void getCurrenciesTestWhenDoesntHaveOnCash() throws NoExchangeRateForThisDateException {
        List<Currency> currencies = exchangeRateService.getCurrencies("18/07/2016");
        Assert.assertNotNull(currencies);
        Assert.assertEquals(155, currencies.size());
    }

    @Test(expected = NoExchangeRateForThisDateException.class)
    public void getCurrenciesTestDoesntHaveExchangeRateForDate() throws NoExchangeRateForThisDateException {
        List<Currency> currencies = exchangeRateService.getCurrencies(Period.getStringDate(Period.plusDays(new Date(), 2)));
        Assert.assertNull(currencies);
    }
}
