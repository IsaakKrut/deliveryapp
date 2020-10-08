package com.isaakkrut.deliveryapp.data.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="item")
public class Item extends Base{

    @Builder
    public Item(Long id, Long categoryId, String name, String calories, double price, String description){
        super(id);
        this.categoryId = categoryId;
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.description = description;
    }

    @Column(name = "category_id")
    private Long categoryId;

    private String name;
    private String calories;
    private double price;
    private String description;

}
