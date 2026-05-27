package com.mystreet.dto;

import com.mystreet.entity.OrderStatus;
import com.mystreet.entity.PaymentMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class OrderDtos {
    public record OrderItemRequest(@NotNull UUID productId, @NotBlank String size, @Min(1) int quantity) {}
    public record OrderRequest(@NotEmpty List<@Valid OrderItemRequest> items, @NotBlank String shippingAddress, @NotNull PaymentMode paymentMode) {}
    public record OrderItemResponse(UUID productId, String productName, String size, int quantity, BigDecimal price) {}
    public record OrderResponse(UUID id, OrderStatus status, PaymentMode paymentMode, BigDecimal totalAmount, String shippingAddress, Instant createdAt, List<OrderItemResponse> items) {}
}
