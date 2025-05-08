package com.orderpulse.order.controller;

import com.orderpulse.dto.order.OrderDto;
import com.orderpulse.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }
}