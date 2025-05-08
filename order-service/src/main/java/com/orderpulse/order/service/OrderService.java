package com.orderpulse.order.service;

import com.orderpulse.dto.inventory.InventoriesDto;
import com.orderpulse.dto.order.OrderDto;
import com.orderpulse.dto.order.OrderItemDto;
import com.orderpulse.order.client.InventoryClient;
import com.orderpulse.order.entity.OrderItems;
import com.orderpulse.order.entity.Orders;
import com.orderpulse.order.mapper.OrderMapper;
import com.orderpulse.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
   private static final String TOPIC = "order-events";
   private InventoryClient inventoryClient;
   private KafkaTemplate<String, String> kafkaTemplate;
   private OrderMapper orderMapper;
   private OrderRepository orderRepository;

   public OrderService(InventoryClient inventoryClient, KafkaTemplate<String, String> kafkaTemplate, OrderMapper orderMapper, OrderRepository orderRepository) {
      this.inventoryClient = inventoryClient;
      this.kafkaTemplate = kafkaTemplate;
      this.orderMapper = orderMapper;
      this.orderRepository = orderRepository;
   }

   @CircuitBreaker(name = "orderServiceCB", fallbackMethod = "fallbackOrder")
   @Retry(name = "orderServiceRetry")
   public OrderDto placeOrder(OrderDto order) {
//       kafkaTemplate.send(TOPIC, order);
      if(areProductsInStock(order)) {
         final Orders mapperOrder = orderMapper.toEntity(order);
         final Orders savedOrder = orderRepository.save(mapperOrder);
         final OrderDto savedOrderDto = orderMapper.toDto(savedOrder);

         Map<String,Integer> inventoryMap = savedOrder.getOrderItems().stream().collect(Collectors.toMap(OrderItems::getProductCode,OrderItems::getQuantity));
         inventoryClient.updateStock(inventoryMap);
         return savedOrderDto;
      }

      return null;
   }

   private boolean areProductsInStock(OrderDto order) {
      final Map<String, Integer> productStock = order.getOrderItemDtos().stream().collect(Collectors.toMap(OrderItemDto::getProductCode, OrderItemDto::getQuantity));
      final ResponseEntity<Boolean> response = inventoryClient.checkStock(new InventoriesDto(productStock));
      if(response.getStatusCode().is2xxSuccessful() && response.getBody()!=null) {
         return response.getBody();
      }

      return false;
   }

   public String fallbackOrder(String order, Throwable t) {
       return "Order failed, fallback response triggered";
   }
}
