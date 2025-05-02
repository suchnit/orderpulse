package com.orderpulse.inventory.mapper.impl;

import com.orderpulse.inventory.dto.InventoryDto;
import com.orderpulse.inventory.entities.Inventory;
import com.orderpulse.inventory.mapper.InventoryMapper;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapperImpl implements InventoryMapper {
    @Override
    public InventoryDto toDto(Inventory entity) {
        if ( entity == null ) {
            return null;
        }

        return new InventoryDto(entity.getProductCode(),entity.getQuantity());
    }

    @Override
    public Inventory toEntity(InventoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        return new Inventory( dto.getProductCode(), dto.getQuantity());
    }
}

