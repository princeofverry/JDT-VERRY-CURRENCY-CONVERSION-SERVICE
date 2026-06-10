package com.indivaragroup.jdt17.currency.conversion.controller;

import com.indivaragroup.jdt17.currency.conversion.config.RateProperties;
import com.indivaragroup.jdt17.currency.conversion.data.AppInfoResponse;
import com.indivaragroup.jdt17.currency.conversion.data.ConversionRequest;
import com.indivaragroup.jdt17.currency.conversion.data.ConversionResponse;
import com.indivaragroup.jdt17.currency.conversion.data.ProfileResponse;
import com.indivaragroup.jdt17.currency.conversion.service.ConverterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class ConverterController {

    private final ConverterService service;
    private final RateProperties rateProperties;

    @GetMapping("/converter")
    public ConversionResponse convert(@Valid ConversionRequest request) {
        return service.convert(
                request.getAmount(),
                request.getFrom(),
                request.getTo()
        );
    }

    @GetMapping("/info")
    public AppInfoResponse info (
            @Value("${app.name}")
            String appName) {
        return new AppInfoResponse(appName);
    }

    @Value("${app.message}")
    private String message;

    @GetMapping("/profile")
    public ProfileResponse profile() {
        return new ProfileResponse(message);
    }

    @GetMapping("/rates")
    public List<Map<String, Object>> rates() {
        return rateProperties.getRates()
                .entrySet()
                .stream()
                .map(entry -> {

                    String[] currencies = entry.getKey().split("_");

                    Map<String, Object> result = new HashMap<>();

                    result.put("from", currencies[0]);
                    result.put("to", currencies[1]);
                    result.put("rate", entry.getValue());

                    return result;
                })
                .toList();
    }
}
