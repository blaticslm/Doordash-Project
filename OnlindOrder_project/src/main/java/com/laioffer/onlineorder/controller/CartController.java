package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.laioffer.onlineorder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @ResponseBody
    public Cart getCart(){
        return cartService.getCart();
        //return new Cart();
    }
}
