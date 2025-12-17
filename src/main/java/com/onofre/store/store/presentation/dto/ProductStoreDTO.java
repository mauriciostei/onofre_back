package com.onofre.store.store.presentation.dto;

import com.onofre.store.core.validations.ExistsInCollection.ExistsInCollection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ProductStoreDTO {
    @NotNull
    @ExistsInCollection(collection = "products", getBy = "_id")
    private String id;

    @NotNull
    @Min(1)
    private BigInteger quantity;
}
