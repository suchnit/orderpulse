package com.orderpulse.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private String productCode;
    private int quantity;
    private double totalPrice;
}
