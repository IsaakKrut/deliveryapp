package com.isaakkrut.deliveryapp.data.domain;

import com.isaakkrut.deliveryapp.data.repository.CategoryRepository;
import com.isaakkrut.deliveryapp.data.services.CategoryService;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class CategoryListDTO {
    private SortedSet<CategoryDTO> categories;

    public CategoryListDTO(Set<Item> items, Set<Category> categories) {
        Comparator<CategoryDTO> nameComparator = Comparator.comparing(CategoryDTO::getName);
        this.categories = new TreeSet<>(nameComparator);
        categories.forEach(category->{
            CategoryDTO dto = new CategoryDTO(category);
            this.categories.add(dto);
        });

        items.forEach(item->{
            for (CategoryDTO category : this.categories){
                if (item.getCategoryId() == category.getId()){
                    category.addItem(item);
                    break;
                }
            }
        });
    }
}
