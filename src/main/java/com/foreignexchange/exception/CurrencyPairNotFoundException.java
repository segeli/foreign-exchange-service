package com.foreignexchange.exception;

public class CurrencyPairNotFoundException extends Exception {

    public CurrencyPairNotFoundException(String currencyPair) {
        super(String.format("Currency Pair Not Found for %s", currencyPair));
    }
}
