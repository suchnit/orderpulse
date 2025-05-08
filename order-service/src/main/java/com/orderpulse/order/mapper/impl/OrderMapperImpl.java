package com.orderpulse.order.mapper.impl;

import com.orderpulse.dto.order.OrderDto;
import com.orderpulse.dto.order.OrderItemDto;
import com.orderpulse.order.entity.Orders;
import com.orderpulse.order.mapper.OrderItemMapper;
import com.orderpulse.order.mapper.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper {
    private OrderItemMapper orderItemMapper;

    public OrderMapperImpl(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public Orders toEntity(OrderDto orderDto) {
        Orders order = new Orders();
        order.setOrderNumber(orderDto.getOrderId());
        order.setCustomerEmail(orderDto.getCustomerEmail());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setStatus(order.getStatus());
        return order;
    }

    @Override
    public OrderDto toDto(Orders orders) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedAt(orders.getCreatedAt());
        orderDto.setOrderId(orders.getOrderNumber());
        orderDto.setStatus(orders.getStatus());
        orderDto.setCustomerEmail(orders.getCustomerEmail());
        final List<OrderItemDto> list = orders.getOrderItems().stream().map(orderItemMapper::toDto).toList();
        orderDto.setOrderItemDtos(list);
        return null;
    }
}
