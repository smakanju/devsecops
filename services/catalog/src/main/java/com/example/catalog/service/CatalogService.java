package com.example.catalog.service;

import com.example.catalog.domain.CatalogItem;
import com.example.catalog.repository.CatalogItemRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogService {

  private final CatalogItemRepository repository;

  public CatalogService(CatalogItemRepository repository) {
    this.repository = repository;
  }

  public List<CatalogItem> listItems() {
    return repository.findAll();
  }

  public CatalogItem getItem(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Catalog item not found"));
  }

  @Transactional
  public CatalogItem createItem(CatalogItem item) {
    if (repository.existsBySku(item.getSku())) {
      throw new IllegalArgumentException("SKU already exists");
    }
    Instant now = Instant.now();
    item.setCreatedAt(now);
    item.setUpdatedAt(now);
    return repository.save(item);
  }

  @Transactional
  public CatalogItem updateItem(Long id, CatalogItem update) {
    CatalogItem existing = getItem(id);
    existing.setName(update.getName());
    existing.setDescription(update.getDescription());
    existing.setPrice(update.getPrice());
    existing.setCurrency(update.getCurrency());
    existing.setUpdatedAt(Instant.now());
    return repository.save(existing);
  }

  @Transactional
  public void deleteItem(Long id) {
    CatalogItem existing = getItem(id);
    repository.delete(existing);
  }
}
