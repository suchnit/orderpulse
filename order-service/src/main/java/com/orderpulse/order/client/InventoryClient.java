package com.orderpulse.order.client;

import com.orderpulse.dto.inventory.InventoryDto;
import com.orderpulse.order.client.fallback.InventoryClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", fallback = InventoryClientFallback.class)
public interface InventoryClient {
    @PostMapping("/api/inventory/check")
    ResponseEntity<Boolean> checkStock(@RequestBody InventoryDto inventoryDto);
}

