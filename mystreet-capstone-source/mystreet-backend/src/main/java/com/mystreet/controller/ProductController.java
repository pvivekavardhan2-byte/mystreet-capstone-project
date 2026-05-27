package com.mystreet.controller;

import com.mystreet.dto.ProductDtos.*;
import com.mystreet.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) { this.productService = productService; }
    @GetMapping public List<ProductResponse> list(@RequestParam(required = false) String brand, @RequestParam(required = false) String size) { return productService.getProducts(brand, size); }
    @GetMapping("/{id}") public ProductResponse get(@PathVariable UUID id) { return productService.getProduct(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public ProductResponse create(@Valid @RequestBody ProductRequest request) { return productService.create(request); }
    @PutMapping("/{id}") public ProductResponse update(@PathVariable UUID id, @Valid @RequestBody ProductRequest request) { return productService.update(id, request); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable UUID id) { productService.delete(id); }
}
