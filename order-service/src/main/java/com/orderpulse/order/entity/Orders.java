package com.orderpulse.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Orders {
    @Id
    @UuidGenerator
    private UUID id;
    private String orderNumber;
    private String customerEmail;
    private String status;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;
}
