package com.orderpulse.order.mapper.impl;

import com.orderpulse.dto.order.OrderItemDto;
import com.orderpulse.order.entity.OrderItems;
import com.orderpulse.order.mapper.OrderItemMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapperImpl implements OrderItemMapper {
    @Override
    public OrderItems toEntity(OrderItemDto orderItemDto) {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrders(orderItems.getOrders());
        orderItems.setQuantity(orderItemDto.getQuantity());
        orderItems.setProductCode(orderItemDto.getProductCode());
        orderItems.setTotalPrice(orderItemDto.getTotalPrice());
        return orderItems;
    }

    @Override
    public OrderItemDto toDto(OrderItems orderItems) {
        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setQuantity(orderItems.getQuantity());
        orderItemDto.setTotalPrice(orderItems.getTotalPrice());
        orderItemDto.setProductCode(orderItems.getProductCode());

        return orderItemDto;
    }
}
