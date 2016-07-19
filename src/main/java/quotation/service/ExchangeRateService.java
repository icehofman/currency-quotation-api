package quotation.service;

import quotation.exception.NoExchangeRateForThisDateException;
import quotation.model.Currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateService {

    private final String URL_EXCHANGE_RATE = "http://www4.bcb.gov.br/Download/fechamento/";
    private final String FORMAT = ".csv";
    private final String DATE_SEPARETOR = "/";

    public List<Currency> getCurrencies(String quotation) throws NoExchangeRateForThisDateException {
        List<Currency> currencies = new ArrayList<Currency>();
        try {
            URL url = new URL(URL_EXCHANGE_RATE + this.changeFormat(quotation) + FORMAT);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null)
                currencies.add(new Currency(line));
            in.close();
        } catch (IOException e) {
            throw new NoExchangeRateForThisDateException(e);
        }
        return currencies;
    }

    private String changeFormat(String quotation) {
        String[] date = quotation.split(DATE_SEPARETOR);
        return date[2] + date[1] + date[0];
    }
}
