package com.mystreet.service;

import com.mystreet.dto.ProductDtos.ProductRequest;
import com.mystreet.entity.Product;
import com.mystreet.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    private final ProductRepository repo = Mockito.mock(ProductRepository.class);
    private final ProductService service = new ProductService(repo);

    @Test
    void createProduct_success() {
        when(repo.save(any(Product.class))).thenAnswer(inv -> { Product p = inv.getArgument(0); p.setId(UUID.randomUUID()); return p; });
        var res = service.create(new ProductRequest("Air Max", "Nike", "Nice", new BigDecimal("100"), "img", "8,9", 10));
        assertThat(res.id()).isNotNull();
        assertThat(res.name()).isEqualTo("Air Max");
    }

    @Test
    void getProduct_success() {
        UUID id = UUID.randomUUID(); Product p = new Product(); p.setId(id); p.setName("Shoe"); p.setBrand("Nike"); p.setPrice(new BigDecimal("99"));
        when(repo.findById(id)).thenReturn(Optional.of(p));
        assertThat(service.getProduct(id).brand()).isEqualTo("Nike");
    }
}
