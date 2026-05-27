package com.mystreet.service;

import com.mystreet.dto.ProductDtos.*;
import com.mystreet.entity.Product;
import com.mystreet.exception.ResourceNotFoundException;
import com.mystreet.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) { this.productRepository = productRepository; }
    public List<ProductResponse> getProducts(String brand, String size) { return productRepository.search(brand, size).stream().map(this::toResponse).toList(); }
    public ProductResponse getProduct(UUID id) { return toResponse(find(id)); }
    public ProductResponse create(ProductRequest req) { Product p = new Product(); copy(req, p); return toResponse(productRepository.save(p)); }
    public ProductResponse update(UUID id, ProductRequest req) { Product p = find(id); copy(req, p); return toResponse(productRepository.save(p)); }
    public void delete(UUID id) { productRepository.delete(find(id)); }
    public Product find(UUID id) { return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id)); }
    private void copy(ProductRequest req, Product p) { p.setName(req.name()); p.setBrand(req.brand()); p.setDescription(req.description()); p.setPrice(req.price()); p.setImageUrl(req.imageUrl()); p.setSizesCsv(req.sizesCsv()); p.setStockQty(req.stockQty()); }
    public ProductResponse toResponse(Product p) { return new ProductResponse(p.getId(), p.getName(), p.getBrand(), p.getDescription(), p.getPrice(), p.getImageUrl(), p.getSizesCsv(), p.getStockQty()); }
}
