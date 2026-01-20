package com.example.catalog.repository;

import com.example.catalog.domain.CatalogItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
  Optional<CatalogItem> findBySku(String sku);
  boolean existsBySku(String sku);
}
