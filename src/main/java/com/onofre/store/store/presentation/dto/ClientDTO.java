package com.onofre.store.store.presentation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDTO {
    @NotNull
    @NotEmpty
    private String type;

    @NotNull
    @NotEmpty
    private String number;

    @NotNull
    @NotEmpty
    private String label;
}
