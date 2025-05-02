package com.orderpulse.inventory.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class InventoryDto implements Serializable {
    private String productCode;
    private int quantity;

    public InventoryDto() {}

    public InventoryDto(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryDto{" +
                "productCode='" + productCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
