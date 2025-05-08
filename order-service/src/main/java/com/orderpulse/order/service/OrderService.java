package com.orderpulse.order.service;

import com.orderpulse.dto.order.OrderDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
   private static final String TOPIC = "order-events";

   @Autowired
   private KafkaTemplate<String, String> kafkaTemplate;

   @CircuitBreaker(name = "orderServiceCB", fallbackMethod = "fallbackOrder")
   @Retry(name = "orderServiceRetry")
   public String placeOrder(OrderDto order) {
//       kafkaTemplate.send(TOPIC, order);
       return "Order placed successfully";
   }

   public String fallbackOrder(String order, Throwable t) {
       return "Order failed, fallback response triggered";
   }
}
