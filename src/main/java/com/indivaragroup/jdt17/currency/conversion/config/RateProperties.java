package com.indivaragroup.jdt17.currency.conversion.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "currency")
public class RateProperties {
    private final Map<String, BigDecimal> rates;
}
