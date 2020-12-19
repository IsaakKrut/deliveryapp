package com.isaakkrut.deliveryapp.web.config;

public enum  UserPermission {
    PLACE_ORDER("order:place"),
    CANCEL_ORDER("order:cancelmy"),
    CANCEL_ALL_ORDERS("order:cancelall"),
    ADD_ADMIN("admin:add"),
    ALL_ORDERS_VIEW("allorders:view"),
    VIEW_MY_ACCOUNT("myAccount:view"),
    VIEW_ALL_ACCOUNTS("accounts:view");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
