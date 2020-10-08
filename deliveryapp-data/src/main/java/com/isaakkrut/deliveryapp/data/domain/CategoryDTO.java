package com.isaakkrut.deliveryapp.data.domain;


import lombok.*;

import java.util.*;


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
        Comparator<Item> nameComparator = Comparator.comparing(Item::getName);
        items = new TreeSet<>(nameComparator);

    }
    private String name;
    private String description;
    SortedSet<Item> items;


    public void addItem(Item item){
        items.add(item);
    }
}
