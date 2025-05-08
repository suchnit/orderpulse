package com.orderpulse.order.mapper;

import com.orderpulse.dto.order.OrderDto;
import com.orderpulse.order.entity.Orders;

public interface OrderMapper {
    Orders toEntity(OrderDto orderDto);
    OrderDto toDto(Orders orders);
}
