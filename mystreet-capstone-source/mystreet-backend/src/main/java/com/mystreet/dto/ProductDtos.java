package com.mystreet.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public class ProductDtos {
    public record ProductRequest(
            @NotBlank String name,
            @NotBlank String brand,
            String description,
            @NotNull @DecimalMin("0.01") BigDecimal price,
            String imageUrl,
            @NotBlank String sizesCsv,
            @Min(0) int stockQty
    ) {}
    public record ProductResponse(UUID id, String name, String brand, String description, BigDecimal price, String imageUrl, String sizesCsv, int stockQty) {}
}
