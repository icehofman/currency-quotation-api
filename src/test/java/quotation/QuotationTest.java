package quotation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import quotation.exception.IllegalDateException;
import quotation.exception.IllegalValueException;
import quotation.exception.NoExchangeRateForThisDateException;
import quotation.exception.NonexistentCurrencyException;
import quotation.utils.DateUtils;

public class QuotationTest {

	private Quotation quotation;

	@Before
	public void setUp() {
		quotation = new Quotation();
	}

	@Test(expected=IllegalValueException.class)
	public void testCurrencyWithNegativeValue() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		quotation.currencyQuotation("USD", "EUR", -1, "20/11/2014");
	}

	@Test(expected=IllegalDateException.class)
	public void testCurrencyWithInvalidDate() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		quotation.currencyQuotation("USD", "EUR", 100.00, "31/02/2014");
	}

	@Test(expected=NoExchangeRateForThisDateException.class)
	public void testCurrencyWithoutExchangeRate() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		quotation.currencyQuotation("USD", "EUR", 100.00, DateUtils.getStringDate(DateUtils.plusDays(new Date(), 2)));
	}

	@Test(expected=NonexistentCurrencyException.class)
	public void testCurrencyWithoutExistentCurrencyOnFrom() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		quotation.currencyQuotation("YYY", "EUR", 100.00, "20/11/2014");
	}

	@Test(expected=NonexistentCurrencyException.class)
	public void testCurrencyWithoutExistentCurrencyOnTo() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		quotation.currencyQuotation("EUR", "ZZZ", 100.00, "20/11/2014");
	}

	@Test
	public void testCurrencyQuotationUSDtoEUR() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		Assert.assertEquals(new BigDecimal(79.69).setScale(2, RoundingMode.CEILING), quotation.currencyQuotation("USD", "EUR", 100.00, "20/11/2014"));
	}

	@Test
	public void testCurrencyQuotationEURtoUSD() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		Assert.assertEquals(new BigDecimal(125.49).setScale(2, RoundingMode.CEILING), quotation.currencyQuotation("EUR", "USD", 100.00, "20/11/2014"));
	}

	@Test
	public void testCurrencyQuotationNZDtoSBD() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		Assert.assertEquals(new BigDecimal(563.70).setScale(2, RoundingMode.FLOOR), quotation.currencyQuotation("NZD", "SBD", 100.00, "20/11/2014"));
	}

	@Test
	public void testCurrencyQuotationSBDtoNZD() throws IllegalValueException, IllegalDateException, NoExchangeRateForThisDateException, NonexistentCurrencyException {
		Assert.assertEquals(new BigDecimal(18.58).setScale(2, RoundingMode.CEILING), quotation.currencyQuotation("SBD", "NZD", 100.00, "17/07/2016"));
	}
}
