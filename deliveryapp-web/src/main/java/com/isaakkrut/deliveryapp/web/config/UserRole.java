package com.isaakkrut.deliveryapp.web.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.thymeleaf.expression.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.isaakkrut.deliveryapp.web.config.UserPermission.*;

public enum UserRole {
    USER(new HashSet<UserPermission>(Arrays.asList(PLACE_ORDER, VIEW_MY_ACCOUNT, CANCEL_ORDER))),
    CREATOR(new HashSet<UserPermission>(Arrays.asList(ADD_ADMIN, ALL_ORDERS_VIEW, CANCEL_ALL_ORDERS, VIEW_ALL_ACCOUNTS))),
    ADMIN(new HashSet<UserPermission>(Arrays.asList(ALL_ORDERS_VIEW, CANCEL_ALL_ORDERS, VIEW_ALL_ACCOUNTS)));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
