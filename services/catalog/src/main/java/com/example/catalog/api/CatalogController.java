package com.example.catalog.api;

import com.example.catalog.domain.CatalogItem;
import com.example.catalog.service.CatalogService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

  private final CatalogService catalogService;

  public CatalogController(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  @GetMapping
  public List<CatalogItemResponse> listItems() {
    return catalogService.listItems().stream()
        .map(CatalogItemMapper::toResponse)
        .toList();
  }

  @GetMapping("/{id}")
  public CatalogItemResponse getItem(@PathVariable Long id) {
    return CatalogItemMapper.toResponse(catalogService.getItem(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CatalogItemResponse createItem(@Valid @RequestBody CatalogItemRequest request) {
    CatalogItem created = catalogService.createItem(CatalogItemMapper.toEntity(request));
    return CatalogItemMapper.toResponse(created);
  }

  @PutMapping("/{id}")
  public CatalogItemResponse updateItem(
      @PathVariable Long id,
      @Valid @RequestBody CatalogItemRequest request) {
    CatalogItem updated = catalogService.updateItem(id, CatalogItemMapper.toEntity(request));
    return CatalogItemMapper.toResponse(updated);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteItem(@PathVariable Long id) {
    catalogService.deleteItem(id);
  }
}
