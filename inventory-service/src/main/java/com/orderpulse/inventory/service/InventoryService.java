package com.orderpulse.inventory.service;

import com.orderpulse.inventory.dto.InventoryDto;
import com.orderpulse.inventory.entity.Inventory;
import com.orderpulse.inventory.mapper.InventoryMapper;
import com.orderpulse.inventory.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public InventoryDto checkInventory(String productCode) {
        final Inventory inventory = inventoryRepository.findByProductCode(productCode).orElseThrow();
        return inventoryMapper.toDto(inventory);
    }

    public InventoryDto create(InventoryDto inventoryDto) throws BadRequestException {
        log.debug("==>> Creating inventory: {}",inventoryDto);

        if(inventoryRepository.findByProductCode(inventoryDto.getProductCode()).isPresent()) {
            throw new BadRequestException("Inventory already exists for the product: "+inventoryDto.getProductCode());
        }

        Inventory inventory = inventoryMapper.toEntity(inventoryDto);
        final Inventory savedInventory = inventoryRepository.save(inventory);
        return inventoryMapper.toDto(savedInventory);
    }
}
