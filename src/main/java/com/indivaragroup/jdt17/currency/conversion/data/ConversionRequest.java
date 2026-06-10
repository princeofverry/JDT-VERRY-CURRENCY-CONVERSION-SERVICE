package com.indivaragroup.jdt17.currency.conversion.data;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConversionRequest {

    @DecimalMin(value = "0.0", inclusive = false,
            message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "From currency cannot be empty")
    private String from;

    @NotBlank(message = "To currency cannot be empty")
    private String to;
}
