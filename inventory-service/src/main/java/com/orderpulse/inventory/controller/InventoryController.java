package com.orderpulse.inventory.controller;

import com.orderpulse.inventory.dto.InventoryDto;
import com.orderpulse.inventory.service.InventoryService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/check")
    public ResponseEntity<InventoryDto> checkInventoryStatus(@RequestParam String productCode) {
        return ResponseEntity.ok(inventoryService.checkInventory(productCode));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) throws BadRequestException {
        return ResponseEntity.ok(inventoryService.create(inventoryDto));
    }
}
