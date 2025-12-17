package com.onofre.store.store.presentation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StoreDTO {
    private String id;

    @NotNull
    @NotEmpty
    private String label;

    @Valid
    private ClientDTO client;

    @NotNull
    @NotEmpty
    private List<@Valid ProductStoreDTO> products;
}
