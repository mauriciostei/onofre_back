package com.onofre.store.products.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ProductDTO {

    private String id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Min(value = 2000, message = "El precio del producto no puede ser inferior a 2000")
    private BigInteger price;
}
