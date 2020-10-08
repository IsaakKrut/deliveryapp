package com.isaakkrut.deliveryapp.data.domain;

import com.isaakkrut.deliveryapp.data.repository.CategoryRepository;
import com.isaakkrut.deliveryapp.data.services.CategoryService;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class CategoryListDTO {
    private final Set<CategoryDTO> categories;

    public CategoryListDTO(Set<Item> items, Set<Category> categories) {
        this.categories = new HashSet<>();
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
