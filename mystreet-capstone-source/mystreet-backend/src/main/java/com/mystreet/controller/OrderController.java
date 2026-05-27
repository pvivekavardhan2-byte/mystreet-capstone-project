package com.mystreet.controller;

import com.mystreet.dto.OrderDtos.*;
import com.mystreet.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) { this.orderService = orderService; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public OrderResponse place(@Valid @RequestBody OrderRequest request) { return orderService.placeOrder(request); }
    @GetMapping("/mine") public List<OrderResponse> mine() { return orderService.myOrders(); }
    @GetMapping("/{id}") public OrderResponse get(@PathVariable UUID id) { return orderService.getOrder(id); }
}
