package com.indivaragroup.jdt17.currency.conversion.service;

import com.indivaragroup.jdt17.currency.conversion.config.RateProperties;
import com.indivaragroup.jdt17.currency.conversion.converter.CurrencyConverter;
import com.indivaragroup.jdt17.currency.conversion.data.ConversionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ConverterService {
    private final CurrencyConverter currencyConverter;
    private final RateProperties rateProperties;

    public ConversionResponse convert(
            BigDecimal amount,
            String from,
            String to
    ) {

        String key = from + "_" + to;

        BigDecimal rate = rateProperties.getRates().get(key);

        BigDecimal result = currencyConverter.convert(amount, from, to);

        return ConversionResponse.builder()
                .amount(amount)
                .from(from)
                .to(to)
                .rate(rate)
                .result(result)
                .build();
    }
}
