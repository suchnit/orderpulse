package com.orderpulse.inventory.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Inventory {
    @Id
    @UuidGenerator
    private UUID id;

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

    private String productCode;
    private int quantity;

    public Inventory(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }
}
