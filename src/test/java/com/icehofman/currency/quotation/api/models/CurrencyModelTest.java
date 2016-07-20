package com.icehofman.currency.quotation.api.models;

import org.junit.Assert;
import org.junit.Test;

public class CurrencyModelTest {

    @Test
    public void currencyConstructor() {
        String line = "20/11/2014;009;A;ETB;0,12590000;0,12720000;20,00200000;20,20200000";
        CurrencyModel currencyModel = new CurrencyModel(line);
        Assert.assertNotNull(currencyModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currencyConstructorWithBrokenLine() {
        String line = "123;23;x;u;j";
        CurrencyModel currencyModel = new CurrencyModel(line);
        Assert.assertNull(currencyModel);
    }
}
