package com.isaakkrut.deliveryapp.data.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category extends Base {

    @Builder
    public Category(Long id, String name, String description){
        super(id);
        this.name = name;
        this.description = description;
    }
    private String name;
    private String description;
}
