package com.isaakkrut.deliveryapp.data.services;

import java.util.Set;

public interface ReadOnlyService <T, ID>{
    Set<T> findAll();

    T findById(ID id);
}
