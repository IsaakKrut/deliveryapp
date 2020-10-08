package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.domain.CategoryListDTO;
import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.User;
import com.isaakkrut.deliveryapp.data.services.CategoryService;
import com.isaakkrut.deliveryapp.data.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"order", "user"})
public class IndexController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    public IndexController(CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @ModelAttribute("order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }


    @RequestMapping({"", "/", "/home"})
    public String getIndexPage(Model model){
        return "index";
    }

    @RequestMapping("/menu")
    public String getMenu(Model model){
        CategoryListDTO categories = new CategoryListDTO(itemService.findAll(), categoryService.findAll());
        model.addAttribute("categoriesDTO", categories);
        return "menu";
    }

    @PostMapping("/order/items/{id}")
    public String addItemToTheCart(@PathVariable Long id, @SessionAttribute("order") Order order){

        Item item = itemService.findById(id);
        order.addItem(item);
        return "redirect:/menu";
    }

    @RequestMapping("/order/items/delete/{id}")
    public String deleteFromCart(@PathVariable Long id, @SessionAttribute("order") Order order){
        order.deleteItemById(id);
        return "redirect:/menu";
    }


    @RequestMapping("/checkout")
    public String getCheckoutPage(Model model){
        return "notImplemented";
    }

    @RequestMapping("/signin")
    public String getLoginPage(Model model, @SessionAttribute("user") User user){
        user.setEmail("krutisaak@yandex.com");
        return "notImplemented";
    }

    @RequestMapping("/register")
    public String getRegistrationPage(Model model){
        return "notImplemented";
    }

    @RequestMapping("/signout")
    public String signout(@SessionAttribute("user") User user){
        user.clear();
        return "redirect:/home";
    }

    @RequestMapping("/account")
    public String getAccountPage(){
        return "notImplemented";
    }

}
