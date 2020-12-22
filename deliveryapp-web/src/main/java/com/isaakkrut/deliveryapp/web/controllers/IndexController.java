package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.converters.UserConverter;
import com.isaakkrut.deliveryapp.data.domain.*;
import com.isaakkrut.deliveryapp.data.dto.CategoryListDTO;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import com.isaakkrut.deliveryapp.data.services.*;
import com.isaakkrut.deliveryapp.security.UserAlreadyExistsException;
import com.isaakkrut.deliveryapp.security.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
@Controller
@SessionAttributes({"order"})
public class IndexController {

    private final EmailService emailService;
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    private final OrderService orderService;
    private final RegistrationService registrationService;
    private final UserConverter userConverter;

    @ModelAttribute("order")
    public Order order(){
        return new Order();
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
        if (item!= null){
            order.addItem(item);
        }
        return "redirect:/menu";
    }

    @RequestMapping("/order/items/delete/{id}")
    public String deleteFromCart(@PathVariable Long id, @SessionAttribute("order") Order order){
        order.deleteItemById(id);
        return "redirect:/menu";
    }


    @RequestMapping("/checkout")
    public String getCheckoutPage(Model model, Principal principal){

        model.addAttribute("email", principal.getName());
        return "checkout";
    }

    @RequestMapping("/signin")
    public String getLoginPage(){
        return "redirect:/account";
    }

    @GetMapping("/login")
    public String login(){
        return "signin";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model, Authentication authentication){
        if (authentication!= null && authentication.isAuthenticated()){
            return "redirect:/account";
        } else {
            model.addAttribute("userDto", new UserDTO());
            return "userform";
        }

    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") @Valid UserDTO userDTO,
                               BindingResult result, Model model){
        if (result.hasErrors()){
            return "userform";
        }

        try{
            registrationService.registerUser(userDTO);
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("message", e.getMessage());
            return "userform";
        }

        emailService.welcomeEmail(userDTO);

        return "redirect:/signin";
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/account")
    public String getAccountPage(Principal principal, Model model){
        User user = userService.getUserByEmail(principal.getName());
        UserDTO userToDisplay = userConverter.userToUserDTO(user);

        Set<Order> orders = orderService.getOrdersByEmail(principal.getName());
        if (orders!= null && orders.size() > 0){
            orders.forEach(userToDisplay::addOrder);
        }

        model.addAttribute("userDto", userToDisplay);
        return "account";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("account/edit")
    public String editAccount(Principal principal, Model model){
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("userDto", userConverter.userToUserDTO(user));
        return "editform";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("account/edit/submit")
    public String editAccountSubmit(@ModelAttribute("userDto") @Valid UserDTO userDTO,
                                    BindingResult result, Model model){

        if (result.hasErrors()){
            return "editform";
        }

        userService.save(userConverter.userDtoToUser(userDTO));

        return "redirect:/account";
    }



    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("account/delete")
    public String deleteAccount(Authentication authentication){
        //TODO: delete account logic
        //userService.deleteUserByEmail(authentication.getName());
        //send last email
        //emailService.deleteAccountEmail(authentication.getName());

        //log out the user
        return "redirect:/signout";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/order/submit")
    public String submitOrder(Principal principal, @SessionAttribute Order order){
        User user = userService.getUserByEmail(principal.getName());
        order.setEmail(user.getEmail());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));

        //save order
        Order savedOrder = orderService.save(order);
        order.setId(savedOrder.getId());

        //send confirmation email
        emailService.sendOrderConfirmation(order);

        //clear Session order
        order.clear();
        return "confirmation";
    }

    @RequestMapping("/accessdenied")
    public String accessDenied(){
        return "accessdenied";
    }



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(LocalDate.class, editor);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public String handleMissingSessionAttribute(){
        return "redirect:/menu";
    }

}
