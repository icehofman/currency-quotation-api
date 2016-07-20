package com.icehofman.currency.quotation.api.exceptions;

public class NoExchangeRateForThisDateException extends Exception {

    private static final long serialVersionUID = -1477671442501985400L;

    public NoExchangeRateForThisDateException(Exception e) {
        super(e);
    }

    public NoExchangeRateForThisDateException() {
        super();
    }
}
