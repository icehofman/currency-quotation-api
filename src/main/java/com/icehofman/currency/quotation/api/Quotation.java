package com.icehofman.currency.quotation.api;

import com.icehofman.currency.quotation.api.exceptions.IllegalDateException;
import com.icehofman.currency.quotation.api.exceptions.IllegalValueException;
import com.icehofman.currency.quotation.api.exceptions.NoExchangeRateForThisDateException;
import com.icehofman.currency.quotation.api.exceptions.NonexistentCurrencyException;
import com.icehofman.currency.quotation.api.models.CurrencyModel;
import com.icehofman.currency.quotation.api.services.CashService;
import com.icehofman.currency.quotation.api.services.ExchangeRateService;
import com.icehofman.currency.quotation.api.models.PeriodModel;

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

        Date date = PeriodModel.getDate(quotationDate);
        quotationDate = this.changeQuotation(date);

        List<CurrencyModel> currencies = cashService.getCurrencies(quotationDate);
        CurrencyModel fromCurrencyModel = this.getCurrency(currencies, from);
        CurrencyModel toCurrencyModel = this.getCurrency(currencies, to);

        BigDecimal result = divide(fromCurrencyModel, toCurrencyModel);
        return this.multiplyOn2Scale(result, value);
    }

    private BigDecimal divide(CurrencyModel fromCurrencyModel, CurrencyModel toCurrencyModel) {
        BigDecimal fromRate = fromCurrencyModel.getSellingRate();
        BigDecimal toRate = toCurrencyModel.getSellingRate();

        return fromRate.divide(toRate, RoundingMode.CEILING);
    }

    private BigDecimal multiplyOn2Scale(BigDecimal amount, Number value) {
        BigDecimal valueToMultiply = new BigDecimal(value.longValue());

        return amount.multiply(valueToMultiply).setScale(2, RoundingMode.CEILING);
    }

    private String changeQuotation(Date date) {
        return PeriodModel.getStringDate(PeriodModel.getDateWithDayOfWeek(date));
    }

    private CurrencyModel getCurrency(List<CurrencyModel> currencies, String currencyName) throws NonexistentCurrencyException {
        for (CurrencyModel currencyModel : currencies) {
            if (currencyModel.getName().equals(currencyName)) {
                return currencyModel;
            }
        }
        throw new NonexistentCurrencyException("name: " + currencyName);
    }
}
