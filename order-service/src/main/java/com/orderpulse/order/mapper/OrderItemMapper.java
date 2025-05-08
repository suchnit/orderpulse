package com.orderpulse.order.mapper;

import com.orderpulse.dto.order.OrderItemDto;
import com.orderpulse.order.entity.OrderItems;

public interface OrderItemMapper {
    OrderItems toEntity(OrderItemDto orderItemDto);
    OrderItemDto toDto(OrderItems orderItems);
}
