package com.icehofman.currency.quotation.api.models;

import org.junit.Assert;
import org.junit.Test;

public class CurrencyTest {

    @Test
    public void CurrencyConstructor() {
        String line = "20/11/2014;009;A;ETB;0,12590000;0,12720000;20,00200000;20,20200000";
        Currency currency = new Currency(line);
        Assert.assertNotNull(currency);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CurrencyConstructorWithBrokenLine() {
        String line = "123;23;x;u;j";
        Currency currency = new Currency(line);
        Assert.assertNull(currency);
    }
}
