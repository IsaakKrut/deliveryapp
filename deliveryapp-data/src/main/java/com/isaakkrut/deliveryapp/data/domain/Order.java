package com.isaakkrut.deliveryapp.data.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="orders")
public class Order extends Base{

    @Builder
    public Order(Long id, double totalPrice, Set<OrderItem> items, String email, Timestamp orderDate){
        super(id);
        this.totalPrice = totalPrice;
        this.items = items;
        this.email = email;
        this.orderDate = orderDate;
    }

    @Column(name="total_price")
    private double totalPrice;

    @OneToMany
    private Set<OrderItem> items = new HashSet<>();

    @Column(name="email")
    private String email;

    @Column(name="order_date")
    private Timestamp orderDate;// = new Timestamp(new Date().getTime());
}
