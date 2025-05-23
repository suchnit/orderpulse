package com.orderpulse.inventory.controller;

import com.orderpulse.dto.inventory.InventoriesDto;
import com.orderpulse.dto.inventory.InventoryDto;
import com.orderpulse.inventory.service.InventoryService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkInventoryStatus(@RequestBody InventoriesDto inventoryDto) {
        return ResponseEntity.ok(inventoryService.checkInventory(inventoryDto));
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Map<String, Integer> inventoryMap) {
        inventoryService.update(inventoryMap);
        return ResponseEntity.ok("Inventory updated");
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) throws BadRequestException {
        return ResponseEntity.ok(inventoryService.create(inventoryDto));
    }
}
