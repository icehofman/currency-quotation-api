package com.icehofman.currency.quotation.api.services;

import com.icehofman.currency.quotation.api.models.CurrencyModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.icehofman.currency.quotation.api.exceptions.NoExchangeRateForThisDateException;
import com.icehofman.currency.quotation.api.models.PeriodModel;

import java.util.Date;
import java.util.List;

public class CashServiceTest {

    private CashService cashService;

    @Before
    public void setUp() throws Exception {
        cashService = new CashService(new ExchangeRateService());
    }

    @Test
    public void getCurrenciesTestWhenDoesntHaveOnCash() throws NoExchangeRateForThisDateException {
        List<CurrencyModel> currencies = cashService.getCurrencies("18/07/2016");
        Assert.assertNotNull(currencies);
        Assert.assertEquals(155, currencies.size());
    }

    @Test(expected = NoExchangeRateForThisDateException.class)
    public void getCurrenciesTestDoesntHaveExchangeRateForDate() throws NoExchangeRateForThisDateException {
        List<CurrencyModel> currencies = cashService.getCurrencies(PeriodModel.getStringDate(PeriodModel.plusDays(new Date(), 2)));
        Assert.assertNull(currencies);
    }
}
