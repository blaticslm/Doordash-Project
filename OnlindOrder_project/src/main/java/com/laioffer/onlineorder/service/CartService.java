package com.laioffer.onlineorder.service;

import com.laioffer.onlineorder.entity.Cart;
import com.laioffer.onlineorder.entity.Customer;
import com.laioffer.onlineorder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.laioffer.onlineorder.dao.CartDao;

@Service
public class CartService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartDao cartDao;

    public Cart getCart() {
        Authentication loggedInUser =  SecurityContextHolder.getContext().getAuthentication(); //先确定authentication
        String username = loggedInUser.getName(); //username is email address. According to the spring security, IT MUST BE USER NAME
        Customer customer = customerService.getCustomer(username);

        if(customer != null) {
            Cart cart = customer.getCart();
            double totalPrice = 0;
            for(OrderItem item: cart.getOrderItemList()){
                totalPrice += item.getPrice() * item.getQuantity();
            }

            cart.setTotalPrice(totalPrice);
            return cart;
        }

        return new Cart();//啥都没有就直接返回空车了
    }

    public void cleanCart() {
        Authentication loggedInUser =  SecurityContextHolder.getContext().getAuthentication(); //先确定authentication
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);
        if (customer  != null) {
            cartDao.removeAllCartItems(customer.getCart());
        }
    }

}
