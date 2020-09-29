package com.isaakkrut.deliveryapp.data.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="item")
@Data
public class Item extends Base{
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String calories;
    private double price;
    private String description;

}
