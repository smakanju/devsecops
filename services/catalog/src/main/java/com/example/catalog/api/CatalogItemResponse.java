package com.example.catalog.api;

import java.math.BigDecimal;
import java.time.Instant;

public record CatalogItemResponse(
    Long id,
    String sku,
    String name,
    String description,
    BigDecimal price,
    String currency,
    Instant createdAt,
    Instant updatedAt) {}
