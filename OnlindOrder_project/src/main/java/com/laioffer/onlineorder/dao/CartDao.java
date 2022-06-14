package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.Cart;
import com.laioffer.onlineorder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao { //checkout

    @Autowired
    SessionFactory sessionFactory;

    public void removeCartItem(int cartitemId) {
        Session session = null;
        try{
            session = sessionFactory.openSession();
            OrderItem cartItem = session.get(OrderItem.class, cartitemId);
            Cart cart = cartItem.getCart();
            cart.getOrderItemList().remove(cartItem);

            session.beginTransaction();
            session.delete(cartItem);
            session.getTransaction().commit();
        } catch(Exception ex){
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    //移除所有，结账所有
    public void removeAllCartItems(Cart cart){
        for(OrderItem item: cart.getOrderItemList()) {
            removeCartItem(item.getId());
        }

    }

}
