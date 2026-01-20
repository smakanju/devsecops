package com.example.catalog;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.catalog.domain.CatalogItem;
import com.example.catalog.repository.CatalogItemRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Testcontainers
class CatalogServiceApplicationTests {

  @Container
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
      .withDatabaseName("catalog")
      .withUsername("catalog")
      .withPassword("catalog");

  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  private CatalogItemRepository repository;

  @Test
  void savesAndLoadsCatalogItem() {
    CatalogItem item = new CatalogItem();
    item.setSku("SKU-001");
    item.setName("Demo Item");
    item.setDescription("Sample description");
    item.setPrice(new BigDecimal("19.99"));
    item.setCurrency("USD");
    item.setCreatedAt(java.time.Instant.now());
    item.setUpdatedAt(java.time.Instant.now());

    CatalogItem saved = repository.save(item);

    assertThat(repository.findById(saved.getId())).isPresent();
  }
}
