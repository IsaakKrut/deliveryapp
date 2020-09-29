package com.isaakkrut.deliveryapp.data.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category extends Base {
    private String name;
    private String description;
}
