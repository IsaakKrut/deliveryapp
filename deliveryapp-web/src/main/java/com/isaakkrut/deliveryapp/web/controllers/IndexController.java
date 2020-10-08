package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.domain.CategoryListDTO;
import com.isaakkrut.deliveryapp.data.domain.Item;
import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.services.CategoryService;
import com.isaakkrut.deliveryapp.data.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("order")
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


    @RequestMapping({"", "/", "/home"})
    public String getIndexPage(Model model){
        model.addAttribute("order", new Order());
        return "index";
    }

    @RequestMapping("/menu")
    public String getMenu(Model model, @ModelAttribute("order")  Order order){
        CategoryListDTO categories = new CategoryListDTO(itemService.findAll(), categoryService.findAll());
        model.addAttribute("categoriesDTO", categories);
        model.addAttribute("order", order);
        return "menu";
    }

    @PostMapping("/order/items/{id}")
    public String addItemToTheCart(@PathVariable Long id, @SessionAttribute("order") Order order){

        Item item = itemService.findById(id);
        order.addItem(item);
        //attributes.addFlashAttribute("order", order);
        return "redirect:/menu";
    }


    @RequestMapping("/checkout")
    public String getCheckoutPage(Model model){
        return "notImplemented";
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model){
        return "notImplemented";
    }

}
