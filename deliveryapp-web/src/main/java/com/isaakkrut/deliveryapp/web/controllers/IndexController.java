package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.services.jpa.CategoryJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private final CategoryJpaService categoryJpaService;

    public IndexController(CategoryJpaService categoryJpaService) {
        this.categoryJpaService = categoryJpaService;
    }


    @RequestMapping({"", "/", "/home"})
    public String getIndexPage(Model model){
        model.addAttribute("number", categoryJpaService.findAll().stream().count());
        return "index";
    }

    @RequestMapping("/menu")
    public String getMenu(){
        return "notImplemented";
    }

    @RequestMapping("/checkout")
    public String getCheckoutPage(){
        return "notImplemented";
    }

    @RequestMapping("/login")
    public String getLoginPage(){
        return "notImplemented";
    }

}
