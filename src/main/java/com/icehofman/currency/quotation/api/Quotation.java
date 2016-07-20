package com.icehofman.currency.quotation.api;

import com.icehofman.currency.quotation.api.exceptions.IllegalDateException;
import com.icehofman.currency.quotation.api.exceptions.IllegalValueException;
import com.icehofman.currency.quotation.api.exceptions.NoExchangeRateForThisDateException;
import com.icehofman.currency.quotation.api.exceptions.NonexistentCurrencyException;
import com.icehofman.currency.quotation.api.models.Currency;
import com.icehofman.currency.quotation.api.services.CashService;
import com.icehofman.currency.quotation.api.services.ExchangeRateService;
import com.icehofman.currency.quotation.api.models.Period;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class Quotation {

    private CashService cashService;

    public Quotation() {
        this.cashService = CashService.getInstance(new ExchangeRateService());
    }

    public BigDecimal currencyQuotation(String from, String to, Number value, String quotationDate) throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
        if (value.intValue() < 0) {
            throw new IllegalValueException();
        }

        Date date = Period.getDate(quotationDate);
        quotationDate = this.changeQuotation(date);

        List<Currency> currencies = cashService.getCurrencies(quotationDate);
        Currency fromCurrency = this.getCurrency(currencies, from);
        Currency toCurrency = this.getCurrency(currencies, to);

        BigDecimal result = divide(fromCurrency, toCurrency);
        return this.multiplyOn2Scale(result, value);
    }

    private BigDecimal divide(Currency fromCurrency, Currency toCurrency) {
        BigDecimal fromRate = fromCurrency.getSellingRate();
        BigDecimal toRate = toCurrency.getSellingRate();

        return fromRate.divide(toRate, RoundingMode.CEILING);
    }

    private BigDecimal multiplyOn2Scale(BigDecimal amount, Number value) {
        BigDecimal valueToMultiply = new BigDecimal(value.longValue());

        return amount.multiply(valueToMultiply).setScale(2, RoundingMode.CEILING);
    }

    private String changeQuotation(Date date) {
        return Period.getStringDate(Period.getDateWithDayOfWeek(date));
    }

    private Currency getCurrency(List<Currency> currencies, String currencyName) throws NonexistentCurrencyException {
        for (Currency currency : currencies) {
            if (currency.getName().equals(currencyName)) {
                return currency;
            }
        }
        throw new NonexistentCurrencyException("name: " + currencyName);
    }
}