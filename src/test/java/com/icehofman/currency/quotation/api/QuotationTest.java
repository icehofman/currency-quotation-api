package com.icehofman.currency.quotation.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.icehofman.currency.quotation.api.exceptions.IllegalDateException;
import com.icehofman.currency.quotation.api.exceptions.IllegalValueException;
import com.icehofman.currency.quotation.api.exceptions.NoExchangeRateForThisDateException;
import com.icehofman.currency.quotation.api.exceptions.NonexistentCurrencyException;
import com.icehofman.currency.quotation.api.models.PeriodModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class QuotationTest {

    private Quotation quotation;

    @Before
    public void setUp() {
        quotation = new Quotation();
    }

    @Test(expected = IllegalValueException.class)
    public void currencyWithNegativeValue() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        quotation.currencyQuotation("USD", "EUR", -1, "20/11/2014");
    }

    @Test(expected = IllegalDateException.class)
    public void currencyWithInvalidDate() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        quotation.currencyQuotation("USD", "EUR", 100.00, "31/02/2014");
    }

    @Test(expected = NoExchangeRateForThisDateException.class)
    public void currencyWithoutExchangeRate() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        quotation.currencyQuotation("USD", "EUR", 100.00, PeriodModel.getStringDate(PeriodModel.plusDays(new Date(), 2)));
    }

    @Test(expected = NonexistentCurrencyException.class)
    public void currencyWithoutExistentCurrencyOnFrom() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        quotation.currencyQuotation("YYY", "EUR", 100.00, "20/11/2014");
    }

    @Test(expected = NonexistentCurrencyException.class)
    public void currencyWithoutExistentCurrencyOnTo() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        quotation.currencyQuotation("EUR", "ZZZ", 100.00, "20/11/2014");
    }

    @Test
    public void currencyQuotationUSDtoEUR() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        Assert.assertEquals(new BigDecimal(79.69).setScale(2, RoundingMode.CEILING), quotation.currencyQuotation("USD", "EUR", 100.00, "20/11/2014"));
    }

    @Test
    public void currencyQuotationEURtoUSD() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        Assert.assertEquals(new BigDecimal(125.49).setScale(2, RoundingMode.CEILING), quotation.currencyQuotation("EUR", "USD", 100.00, "20/11/2014"));
    }

    @Test
    public void currencyQuotationNZDtoSBD() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        Assert.assertEquals(new BigDecimal(563.70).setScale(2, RoundingMode.FLOOR), quotation.currencyQuotation("NZD", "SBD", 100.00, "20/11/2014"));
    }

    @Test
    public void currencyQuotationSBDtoNZD() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        Assert.assertEquals(new BigDecimal(18.58).setScale(2, RoundingMode.CEILING), quotation.currencyQuotation("SBD", "NZD", 100.00, "17/07/2016"));
    }
}
