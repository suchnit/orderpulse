package com.orderpulse.inventory.mapper;

import com.orderpulse.inventory.dto.InventoryDto;
import com.orderpulse.inventory.entities.Inventory;

public interface InventoryMapper {
    InventoryDto toDto(Inventory entity);
    Inventory toEntity(InventoryDto dto);
}
