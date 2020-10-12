package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.converters.UserConverter;
import com.isaakkrut.deliveryapp.data.domain.*;
import com.isaakkrut.deliveryapp.data.dto.CategoryListDTO;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import com.isaakkrut.deliveryapp.data.services.CategoryService;
import com.isaakkrut.deliveryapp.data.services.ItemService;
import com.isaakkrut.deliveryapp.data.services.UserService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Controller
@SessionAttributes({"order", "user"})
public class IndexController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;

    public IndexController(CategoryService categoryService, ItemService itemService, UserService userService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userService = userService;
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
    public String getIndexPage(){
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
    public String getCheckoutPage(Model model, @SessionAttribute("user") User user){
        if (user.isEmpty()) {
            return "redirect:/signin";
        }
        return "checkout";
    }

    @RequestMapping("/signin")
    public String getLoginPage(Model model){
        model.addAttribute("login", new Login());
        return "signin";
    }

    @PostMapping("/signin")
    public String signIn(Model model, @ModelAttribute("login") Login login, @ModelAttribute User user){

        if ( user!= null && userService.validateUser(login)){
            User signedInUser = userService.getUserByEmail(login.getUserName());
            user.setEmail(signedInUser.getEmail());
            user.setPassword(signedInUser.getPassword());
            user.setFirstName(signedInUser.getFirstName());
            user.setLastName(signedInUser.getLastName());
            user.setBirthDate(signedInUser.getBirthDate());
            user.setId(signedInUser.getId());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid login or password");
            return "signin";
        }

    }

    @RequestMapping("/register")
    public String getRegistrationPage(Model model, @SessionAttribute("user") User user){
        if (user.isEmpty()) {
            model.addAttribute("userDTO", new UserDTO());
            return "registration";
        }
        else return "redirect:/home";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute UserDTO newUser,
                               BindingResult result, @SessionAttribute User user){
        if (result.hasErrors()){
            return "registration";
        }
        if (userService.getUserByEmail(newUser.getDtoEmail()) != null){
            newUser.setDtoEmail(null);
            return "registration";
        }

        //saving the new user
        User savedUser = userService.save(UserConverter.userDtoToUser(newUser));

        //storing the new user in the session attribute (signing in)
        user.setId(savedUser.getId());
        user.setEmail(savedUser.getEmail());
        user.setPassword(savedUser.getPassword());
        user.setFirstName(savedUser.getFirstName());
        user.setLastName(savedUser.getLastName());
        user.setBirthDate(savedUser.getBirthDate());

        return "redirect:/home";
    }

    @RequestMapping("/signout")
    public String signout(@SessionAttribute("user") User user){
        user.clear();
        return "redirect:/home";
    }

    @RequestMapping("/account")
    public String getAccountPage(@SessionAttribute User user){
        return "account";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(LocalDate.class, editor);
    }

}
