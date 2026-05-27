package com.mystreet.service;

import com.mystreet.dto.OrderDtos.*;
import com.mystreet.entity.*;
import com.mystreet.exception.BadRequestException;
import com.mystreet.exception.ResourceNotFoundException;
import com.mystreet.repository.OrderRepository;
import com.mystreet.repository.ProductRepository;
import com.mystreet.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) { this.orderRepository = orderRepository; this.productRepository = productRepository; this.userRepository = userRepository; }
    @Transactional
    public OrderResponse placeOrder(OrderRequest req) {
        User user = currentUser();
        Order order = new Order(); order.setUser(user); order.setShippingAddress(req.shippingAddress()); order.setPaymentMode(req.paymentMode()); order.setStatus(OrderStatus.PLACED);
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemRequest itemReq : req.items()) {
            Product p = productRepository.findById(itemReq.productId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            if (p.getStockQty() < itemReq.quantity()) throw new BadRequestException("Insufficient stock for " + p.getName());
            p.setStockQty(p.getStockQty() - itemReq.quantity());
            OrderItem item = new OrderItem(); item.setOrder(order); item.setProduct(p); item.setSize(itemReq.size()); item.setQuantity(itemReq.quantity()); item.setPrice(p.getPrice());
            order.getItems().add(item);
            total = total.add(p.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity())));
        }
        order.setTotalAmount(total);
        return toResponse(orderRepository.save(order));
    }
    public List<OrderResponse> myOrders() { return orderRepository.findByUserOrderByCreatedAtDesc(currentUser()).stream().map(this::toResponse).toList(); }
    public OrderResponse getOrder(UUID id) { Order o = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found")); if (!o.getUser().getId().equals(currentUser().getId())) throw new BadRequestException("You cannot access this order"); return toResponse(o); }
    private User currentUser() { String email = SecurityContextHolder.getContext().getAuthentication().getName(); return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found")); }
    private OrderResponse toResponse(Order o) { return new OrderResponse(o.getId(), o.getStatus(), o.getPaymentMode(), o.getTotalAmount(), o.getShippingAddress(), o.getCreatedAt(), o.getItems().stream().map(i -> new OrderItemResponse(i.getProduct().getId(), i.getProduct().getName(), i.getSize(), i.getQuantity(), i.getPrice())).toList()); }
}
