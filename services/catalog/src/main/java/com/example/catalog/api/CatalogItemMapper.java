package com.example.catalog.api;

import com.example.catalog.domain.CatalogItem;

public final class CatalogItemMapper {

  private CatalogItemMapper() {}

  public static CatalogItemResponse toResponse(CatalogItem item) {
    return new CatalogItemResponse(
        item.getId(),
        item.getSku(),
        item.getName(),
        item.getDescription(),
        item.getPrice(),
        item.getCurrency(),
        item.getCreatedAt(),
        item.getUpdatedAt());
  }

  public static CatalogItem toEntity(CatalogItemRequest request) {
    CatalogItem item = new CatalogItem();
    item.setSku(request.sku());
    item.setName(request.name());
    item.setDescription(request.description());
    item.setPrice(request.price());
    item.setCurrency(request.currency());
    return item;
  }
}
