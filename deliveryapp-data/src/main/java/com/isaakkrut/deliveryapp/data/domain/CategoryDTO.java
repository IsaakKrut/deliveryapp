package com.isaakkrut.deliveryapp.data.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO extends Base {

    @Builder
    public CategoryDTO(Long id, String name, String description){
        super(id);
        this.name = name;
        this.description = description;
    }

    public CategoryDTO(Category category){
        this.description = category.getDescription();
        this.name = category.getName();
        this.setId(category.getId());
    }
    private String name;
    private String description;
    Set<Item> items = new HashSet<>();

    public void addItem(Item item){
        items.add(item);
    }
}
