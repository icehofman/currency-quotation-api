package quotation.service;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import quotation.exception.NoExchangeRateForThisDateException;
import quotation.model.Currency;
import quotation.utils.DateUtils;

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
	
	@Test(expected=NoExchangeRateForThisDateException.class)
	public void getCurrenciesTestDoesntHaveExchangeRateForDate() throws NoExchangeRateForThisDateException {
		List<Currency> currencies = exchangeRateService.getCurrencies(DateUtils.getStringDate(DateUtils.plusDays(new Date(), 2)));
		Assert.assertNull(currencies);
	}
}
