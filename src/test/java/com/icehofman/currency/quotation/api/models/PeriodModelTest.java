package com.icehofman.currency.quotation.api.models;

import org.junit.Assert;
import org.junit.Test;
import com.icehofman.currency.quotation.api.exceptions.IllegalDateException;

import java.util.Date;

public class PeriodModelTest {

    @Test(expected = IllegalDateException.class)
    public void getDateWithInvalidDate() throws IllegalDateException {
        Assert.assertNull(PeriodModel.getDate("31/02/2016"));
    }

    @Test
    public void getDate() throws IllegalDateException {
        Assert.assertNotNull(PeriodModel.getDate("18/07/2016"));
    }

    @Test
    public void getStringDate() throws IllegalDateException {
        Assert.assertEquals("18/07/2016", PeriodModel.getStringDate(PeriodModel.getDate("18/07/2016")));
    }

    @Test
    public void getDateWithDayOfWeekWhenIsSaturday() throws IllegalDateException {
        Assert.assertEquals(PeriodModel.getDate("15/07/2016"), PeriodModel.getDateWithDayOfWeek(PeriodModel.getDate("16/07/2016")));
    }

    @Test
    public void getDateWithDayOfWeekWhenIsSunday() throws IllegalDateException {
        Assert.assertEquals(PeriodModel.getDate("15/07/2016"), PeriodModel.getDateWithDayOfWeek(PeriodModel.getDate("15/07/2016")));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void getDateWithDayOfWeekWhenIsMonday() {
        Assert.assertEquals(new Date(2016, 7, 9), PeriodModel.getDateWithDayOfWeek(new Date(2016, 7, 9)));
    }

    @Test
    public void minusDays() throws IllegalDateException {
        Assert.assertEquals(PeriodModel.getDate("17/07/2016"), PeriodModel.minusDays(PeriodModel.getDate("18/07/2016"), 1));
    }

    @Test
    public void plusDays() throws IllegalDateException {
        Assert.assertEquals(PeriodModel.getDate("17/07/2016"), PeriodModel.plusDays(PeriodModel.getDate("16/07/2016"), 1));
    }
}
