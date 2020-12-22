package com.isaakkrut.deliveryapp.data.services;

import com.isaakkrut.deliveryapp.data.domain.Order;
import com.isaakkrut.deliveryapp.data.domain.OrderItem;
import com.isaakkrut.deliveryapp.data.domain.User;
import com.isaakkrut.deliveryapp.data.dto.UserDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class GmailService implements EmailService {
    private final JavaMailSender emailSender;

    public GmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    public void sendOrderConfirmation(Order order) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

        StringBuilder emailBody = new StringBuilder("Your order has been placed\n\n\nOrder Number:   " + order.getId() +
                                "\nOrder Date :   " + dateFormat.format(order.getOrderDate()) +
                                "\nOrder Total:   $" + order.getTotalPrice() +
                                "\n\n");
        int count = 1;
        for (OrderItem item : order.getItems()){
            emailBody.append(count++ + ". " + item.getItem().getName() +
                            "  " + item.getItem().getPrice() +
                            " x " + item.getQuantity() +
                            " = " + item.getQuantity() * item.getItem().getPrice() + "\n");
        }
        emailBody.append("\n\n\nThanks for ordering with us" +
                            "\n\nIsaak Krut, IsaakDelivery");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("IsaakDelivery@gmail.com");
        message.setTo(order.getEmail());
        message.setSubject("Order Placed");
        message.setText(emailBody.toString());
        emailSender.send(message);

    }

    @Override
    public void welcomeEmail(UserDTO user) {

        StringBuilder emailBody = new StringBuilder("Welcome, " +
                                    user.getDtoFirstName() + " " + user.getDtoLastName() +
                                    "\n\nWe can't wait until you make your first order with us. Enjoy our deals!!!" +
                                    "\n\n\nIsaak Krut, IsaakDelivery");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("IsaakDelivery@gmail.com");
        message.setTo(user.getDtoEmail());
        message.setSubject("Welcome to IsaakDelivery");
        message.setText(emailBody.toString());
        emailSender.send(message);
    }

    @Override
    public void deleteAccountEmail(String user) {

            StringBuilder emailBody = new StringBuilder("IsaakDelivery\n\n" +
                    user +
                    ", we are sorry to see you go" +
                    "\n\n\nIsaak Krut, IsaakDelivery");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("IsaakDelivery@gmail.com");
            message.setTo(user);
            message.setSubject("Account deleted");
            message.setText(emailBody.toString());
            emailSender.send(message);
    }

}
