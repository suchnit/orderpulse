package com.orderpulse.order.client;

import com.orderpulse.dto.inventory.InventoriesDto;
import com.orderpulse.order.client.fallback.InventoryClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "inventory-service", fallback = InventoryClientFallback.class)
public interface InventoryClient {
    @GetMapping("/api/inventory/check")
    ResponseEntity<Boolean> checkStock(@RequestBody InventoriesDto inventoryDto);

    @PutMapping("/api/inventory")
    ResponseEntity<String> updateStock(@RequestBody Map<String, Integer> inventoryDto);
}

