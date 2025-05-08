package com.orderpulse.inventory.mapper;

import com.orderpulse.dto.inventory.InventoryDto;
import com.orderpulse.inventory.entity.Inventory;

public interface InventoryMapper {
    InventoryDto toDto(Inventory entity);
    Inventory toEntity(InventoryDto dto);
}
