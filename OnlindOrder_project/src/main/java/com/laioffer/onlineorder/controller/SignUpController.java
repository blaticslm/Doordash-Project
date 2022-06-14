package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.laioffer.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;



@Controller
public class SignUpController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "/signup", method = RequestMethod.POST) //controller来map到model里，
    @ResponseStatus(value = HttpStatus.CREATED)

    public void signup(@RequestBody Customer customer) { //返回一个json值， HTML的body部分
        //System.out.println(customer.getEmail()); //if I see the emial on console, meaning that my request is successful
        customerService.signUp(customer);

    }


}
