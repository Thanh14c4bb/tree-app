package com.project.treeshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "phone_number",nullable = false,length = 20)
    private String phoneNumber;

    @Column(name = "name",nullable = false,length = 30)
    private String name;

    @Column(name = "address",nullable = false,length = 100)
    private String address;

    @Column(name = "note",length = 100)
    private String note;


    @Column(name = "total_money",nullable = false)
    private float totalMoney;

    @Column(name = "status",nullable = false,length = 20)
    private String status;

    @Column(name = "order_date")
    private LocalDateTime oderDate;
    @PrePersist
    protected void onCreate() {
        oderDate =  LocalDateTime.now();
    }

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
