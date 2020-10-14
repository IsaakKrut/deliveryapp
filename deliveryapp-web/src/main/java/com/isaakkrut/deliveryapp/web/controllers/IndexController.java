package com.isaakkrut.deliveryapp.web.controllers;

import com.isaakkrut.deliveryapp.data.converters.UserConverter;
import com.isaakkrut.deliveryapp.data.domain.*;
import com.isaakkrut.deliveryapp.data.dto.CategoryListDTO;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import com.isaakkrut.deliveryapp.data.services.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Controller
@SessionAttributes({"order", "user"})
public class IndexController {

    private final EmailService emailService;
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final UserService userService;
    private final OrderService orderService;

    public IndexController(EmailService emailService, CategoryService categoryService,
                           ItemService itemService, UserService userService, OrderService orderService) {
        this.emailService = emailService;
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.userService = userService;
        this.orderService = orderService;
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
    public String signIn(Model model, @ModelAttribute("login") Login login, @SessionAttribute User user){

        if ( user!= null && userService.validateUser(login)){
            User signedInUser = userService.getUserByEmail(login.getUserName());
            user.setEmail(signedInUser.getEmail());
            user.setPassword(signedInUser.getPassword());
            user.setFirstName(signedInUser.getFirstName());
            user.setLastName(signedInUser.getLastName());
            user.setBirthDate(signedInUser.getBirthDate());
            user.setId(signedInUser.getId());
            return "redirect:/account";
        } else {
            model.addAttribute("error", "Invalid login or password");
            return "signin";
        }

    }

    @RequestMapping("/register")
    public String getRegistrationPage(@SessionAttribute("user") User user, UserDTO userDTO){
        if (user.isEmpty()) {
            //model.addAttribute("userDTO", new UserDTO());
            return "userform";
        }
        else return "redirect:/account";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDTO userDTO,
                               BindingResult result, @SessionAttribute User user){
        if (result.hasErrors()){
            return "userform";
        }
        if (user.isEmpty() && userService.getUserByEmail(userDTO.getDtoEmail()) != null){
            result.rejectValue("dtoEmail", "user.emailerror", "User already exists");
            return "userform";
        }

        //saving the user
        User userToSave = UserConverter.userDtoToUser(userDTO);
        User savedUser = userService.save(userToSave);

        //storing the new user in the session attribute (signing in)
        user.setId(savedUser.getId());
        user.setEmail(savedUser.getEmail());
        user.setPassword(savedUser.getPassword());
        user.setFirstName(savedUser.getFirstName());
        user.setLastName(savedUser.getLastName());
        user.setBirthDate(savedUser.getBirthDate());

        return "redirect:/account";
    }

    @RequestMapping("/signout")
    public String signout(@SessionAttribute("user") User user){
        user.clear();
        return "redirect:/home";
    }

    @RequestMapping("/account")
    public String getAccountPage(@SessionAttribute User user, Model model){
        if (user.isEmpty()){
            return "redirect:/signin";
        }
        UserDTO userToDisplay = UserConverter.userToUserDTO(user);
        orderService.getOrdersByEmail(userToDisplay.getDtoEmail()).forEach(userToDisplay::addOrder);
        model.addAttribute("userDto", userToDisplay);
        return "account";
    }

    @RequestMapping("account/edit")
    public String editAccount(@SessionAttribute User user, Model model){
        model.addAttribute(UserConverter.userToUserDTO(user));
        return "userform";
    }

    @RequestMapping("/order/submit")
    public String submitOrder(@SessionAttribute User user, @SessionAttribute Order order){
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



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(LocalDate.class, editor);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public String handleMissingSessionAttribute(){
        return "redirect:/";
    }

}
