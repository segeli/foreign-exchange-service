package com.foreignexchange.client.model;

import java.util.Map;

public record ExchangeRateResponse(boolean success, long timestamp, String base, String date, Map<String, Double> rates) {
}
