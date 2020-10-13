package com.isaakkrut.deliveryapp.data.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    private double totalPrice = 0.0;

    @OneToMany
    private Set<OrderItem> items = new HashSet<>();

    @Column(name="email")
    private String email;

    @Column(name="order_date")
    private Timestamp orderDate;

    public void addOrderItem(OrderItem orderItem){
        this.items.add(orderItem);
    }

    public void addItem(Item item){

        if (this.items == null){
            this.items = new HashSet<>();
        }
        boolean doesExist = false;
        this.totalPrice += item.getPrice();

        for (OrderItem orderItem : items){
            if (orderItem.getItem().getId() == item.getId()){
                // if item is already in the card increase quantity by one
                doesExist = true;
                orderItem.setQuantity(orderItem.getQuantity() + 1);
                break;
            }
        }
        if (!doesExist){
            items.add(OrderItem.builder().order(this).item(item).quantity(1).build());
        }
    }

    public void deleteItemById(Long id){
        for (OrderItem orderItem : items){
            if (orderItem.getItem().getId() == id){
                // if item is already in the card increase quantity by one
                this.items.remove(orderItem);
                totalPrice = 0.0;
                for (OrderItem item : items){
                    totalPrice+= item.getQuantity()*item.getItem().getPrice();
                }
                break;
            }
        }
    }

    public void clear(){
        this.totalPrice = 0.0;
        this.email = null;
        this.items = null;
        this.orderDate = null;
        this.setId(null);
    }
}
