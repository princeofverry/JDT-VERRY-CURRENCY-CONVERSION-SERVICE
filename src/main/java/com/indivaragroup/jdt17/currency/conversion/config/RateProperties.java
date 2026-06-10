package com.indivaragroup.jdt17.currency.conversion.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "currency")
public class RateProperties {
    private Map<String, BigDecimal> rates;
}
