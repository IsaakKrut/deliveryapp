package com.isaakkrut.deliveryapp.data.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="order_items")
@Data
public class OrderItem extends Base{
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;

}
