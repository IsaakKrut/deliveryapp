package com.isaakkrut.deliveryapp.data.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    public Item(Long id, Category category, String name, String calories, double price, String description){
        super(id);
        this.category = category;
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private String calories;
    private double price;
    private String description;

}
