package com.orderpulse.inventory.service;

import com.orderpulse.dto.inventory.InventoriesDto;
import com.orderpulse.dto.inventory.InventoryDto;
import com.orderpulse.inventory.entity.Inventory;
import com.orderpulse.inventory.mapper.InventoryMapper;
import com.orderpulse.inventory.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class InventoryService {
    private final InventoryMapper inventoryMapper;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryMapper inventoryMapper,InventoryRepository inventoryRepository) {
        this.inventoryMapper = inventoryMapper;
        this.inventoryRepository = inventoryRepository;
    }

    private static void accept(Inventory val) {
//        throw new BadRequestException()
    }

    public Boolean checkInventory(InventoriesDto inventoryDto) {
        return inventoryDto.getProductStock().entrySet().stream().allMatch(this::isProductInStock);
    }

    public void update(Map<String, Integer> inventoryDto) {
        inventoryDto.entrySet().forEach(this::update);
    }

    public InventoryDto create(InventoryDto inventoryDto) throws BadRequestException {
        log.debug("==>> Creating inventory:::: productCode: {}, quantity: {}",inventoryDto.getProductCode(),inventoryDto.getQuantity());

        if(inventoryRepository.findByProductCode(inventoryDto.getProductCode()).isPresent()) {
            throw new BadRequestException("Inventory already exists for the product: "+inventoryDto.getProductCode());
        }

        Inventory inventory = inventoryMapper.toEntity(inventoryDto);
        final Inventory savedInventory = inventoryRepository.save(inventory);
        return inventoryMapper.toDto(savedInventory);
    }

    private boolean isProductInStock(Map.Entry<String,Integer> product) {
        final Inventory inventory = inventoryRepository.findByProductCode(product.getKey()).orElseThrow();
        return inventory.getQuantity()>=product.getValue();
    }

    private void update(Map.Entry<String, Integer> inventory) {
        final Inventory inventoryFromDB = inventoryRepository.findByProductCode(inventory.getKey()).orElseThrow();
        inventoryFromDB.setQuantity(inventoryFromDB.getQuantity() - inventory.getValue());
        inventoryRepository.save(inventoryFromDB);
    }
}
