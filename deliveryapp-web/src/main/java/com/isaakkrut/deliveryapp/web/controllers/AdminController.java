package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.services.OrderService;
import com.isaakkrut.deliveryapp.data.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final OrderService orderService;
    private final UserService userService;

    public AdminController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"", "/home"})
    public String getHomepage(){
        return "admin/home";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/orders")
    public String getAllOrders(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "admin/orders";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }


}
