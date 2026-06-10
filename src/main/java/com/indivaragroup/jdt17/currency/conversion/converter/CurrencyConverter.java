package com.indivaragroup.jdt17.currency.conversion.converter;

import com.indivaragroup.jdt17.currency.conversion.config.RateProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CurrencyConverter {
    private final RateProperties rateProperties;

    public BigDecimal convert(
            BigDecimal amount,
            String from,
            String to
    ) {

        String key = from + "_" + to;

        BigDecimal rate =
                rateProperties.getRates().get(key);

        if (rate == null) {
            throw new IllegalArgumentException(
                    "Rate not found"
            );
        }

        return amount.multiply(rate);
    }
}
