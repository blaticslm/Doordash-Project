package com.laioffer.onlineorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.laioffer.onlineorder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class CheckOutController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void checkout() {
        cartService.cleanCart();
    }

}
