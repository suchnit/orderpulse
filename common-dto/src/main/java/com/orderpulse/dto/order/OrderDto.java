package com.orderpulse.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    private String orderId;
    private String customerEmail;
    private String status;
    private LocalDate createdAt;
    private List<OrderItem> orderItems;
}
