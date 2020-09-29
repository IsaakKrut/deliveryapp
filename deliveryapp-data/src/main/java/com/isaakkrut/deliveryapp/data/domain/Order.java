package com.isaakkrut.deliveryapp.data.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="orders")
public class Order extends Base{

    @Column(name="total_price")
    private double totalPrice;

    @OneToMany
    private Set<OrderItem> items = new HashSet<>();

    @Column(name="email")
    private String email;

    @Column(name="order_date")
    private Timestamp orderDate;// = new Timestamp(new Date().getTime());
}
