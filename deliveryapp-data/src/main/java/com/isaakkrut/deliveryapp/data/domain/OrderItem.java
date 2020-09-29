package com.isaakkrut.deliveryapp.data.domain;


import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Table(name="order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem extends Base{

    @Builder
    public OrderItem(Long id, Order order, Item item, int quantity){
        super(id);
        this.order = order;
        this.item = item;
        this.quantity = quantity;
    }
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;

}
