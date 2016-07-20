package com.icehofman.currency.quotation.api.models;

import org.junit.Assert;
import org.junit.Test;
import com.icehofman.currency.quotation.api.exceptions.IllegalDateException;

import java.util.Date;

public class PeriodTest {

    @Test(expected = IllegalDateException.class)
    public void testGetDateWithInvalidDate() throws IllegalDateException {
        Assert.assertNull(Period.getDate("31/02/2016"));
    }

    @Test
    public void testGetDate() throws IllegalDateException {
        Assert.assertNotNull(Period.getDate("18/07/2016"));
    }

    @Test
    public void testGetStringDate() throws IllegalDateException {
        Assert.assertEquals("18/07/2016", Period.getStringDate(Period.getDate("18/07/2016")));
    }

    @Test
    public void testgetDateWithDayOfWeekWhenIsSaturday() throws IllegalDateException {
        Assert.assertEquals(Period.getDate("15/07/2016"), Period.getDateWithDayOfWeek(Period.getDate("16/07/2016")));
    }

    @Test
    public void testgetDateWithDayOfWeekWhenIsSunday() throws IllegalDateException {
        Assert.assertEquals(Period.getDate("15/07/2016"), Period.getDateWithDayOfWeek(Period.getDate("15/07/2016")));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testgetDateWithDayOfWeekWhenIsMonday() {
        Assert.assertEquals(new Date(2016, 7, 9), Period.getDateWithDayOfWeek(new Date(2016, 7, 9)));
    }

    @Test
    public void testMinusDays() throws IllegalDateException {
        Assert.assertEquals(Period.getDate("17/07/2016"), Period.minusDays(Period.getDate("18/07/2016"), 1));
    }

    @Test
    public void testPlusDays() throws IllegalDateException {
        Assert.assertEquals(Period.getDate("17/07/2016"), Period.plusDays(Period.getDate("16/07/2016"), 1));
    }
}
