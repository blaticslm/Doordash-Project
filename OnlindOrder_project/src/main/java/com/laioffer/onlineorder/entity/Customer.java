package com.laioffer.onlineorder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customer implements Serializable  {

    //field of entries
    @Id
    private String email;

    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;


    //cart 是 foreign key
    @OneToOne(cascade = CascadeType.ALL) //?????????????
    @JoinColumn(unique = true) //因为一个人只有一个车，为了保证value是唯一的， 以及一对一用这个比较ok
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }




    //bunch getter and setter for fields above
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return  enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



}
