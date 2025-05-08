package com.orderpulse.order.client.fallback;

import com.orderpulse.dto.inventory.InventoryDto;
import com.orderpulse.order.client.InventoryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryClientFallback implements InventoryClient {
    @Override
    public ResponseEntity<Boolean> checkStock(InventoryDto inventoryDto) {
        return ResponseEntity.ok(false); // fallback behavior
    }
}

