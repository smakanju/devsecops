package com.example.catalog.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CatalogItemRequest(
    @NotBlank @Size(max = 64) String sku,
    @NotBlank @Size(max = 128) String name,
    @Size(max = 512) String description,
    @NotNull BigDecimal price,
    @NotBlank @Size(max = 3) String currency) {}
