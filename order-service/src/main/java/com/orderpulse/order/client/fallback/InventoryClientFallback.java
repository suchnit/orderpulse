package com.orderpulse.order.client.fallback;

import com.orderpulse.dto.inventory.InventoriesDto;
import com.orderpulse.order.client.InventoryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InventoryClientFallback implements InventoryClient {
    @Override
    public ResponseEntity<Boolean> checkStock(InventoriesDto inventoryDto) {
        return ResponseEntity.ok(false); // fallback behavior
    }

    @Override
    public ResponseEntity<String> updateStock(Map<String, Integer> inventoryDto) {
        return ResponseEntity.ok("Service not available"); // fallback behavior
    }
}

