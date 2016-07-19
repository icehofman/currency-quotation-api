package quotation.utils;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import quotation.exception.IllegalDateException;

public class DateUtillsTest {
	
	@Test(expected=IllegalDateException.class)
	public void testGetDateWithInvalidDate() throws IllegalDateException {
		Assert.assertNull(DateUtils.getDate("31/02/2016"));
	}
	
	@Test
	public void testGetDate() throws IllegalDateException {
		Assert.assertNotNull(DateUtils.getDate("18/07/2016"));
	}
	
	@Test
	public void testGetStringDate() throws IllegalDateException {
		Assert.assertEquals("18/07/2016", DateUtils.getStringDate(DateUtils.getDate("18/07/2016")));
	}
	
	@Test
	public void testgetDateWithDayOfWeekWhenIsSaturday() throws IllegalDateException {
		Assert.assertEquals(DateUtils.getDate("15/07/2016"), DateUtils.getDateWithDayOfWeek(DateUtils.getDate("16/07/2016")));
	}
	
	@Test
	public void testgetDateWithDayOfWeekWhenIsSunday() throws IllegalDateException {
		Assert.assertEquals(DateUtils.getDate("15/07/2016"), DateUtils.getDateWithDayOfWeek(DateUtils.getDate("15/07/2016")));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testgetDateWithDayOfWeekWhenIsMonday() {
		Assert.assertEquals(new Date(2016, 7, 9), DateUtils.getDateWithDayOfWeek(new Date(2016, 7, 9)));
	}
	
	@Test
	public void testMinusDays() throws IllegalDateException {
		Assert.assertEquals(DateUtils.getDate("17/07/2016"), DateUtils.minusDays(DateUtils.getDate("18/07/2016"), 1));
	}
	
	@Test
	public void testPlusDays() throws IllegalDateException {
		Assert.assertEquals(DateUtils.getDate("17/07/2016"), DateUtils.plusDays(DateUtils.getDate("16/07/2016"), 1));
	}
}
