package com.orderpulse.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class OrderItems {
    @Id
    @UuidGenerator
    private UUID id;
    private String productCode;
    private int quantity;
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;
}
