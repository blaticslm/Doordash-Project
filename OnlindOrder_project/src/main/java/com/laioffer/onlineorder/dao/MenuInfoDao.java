package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.MenuItem;
import com.laioffer.onlineorder.entity.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants() {
        Session session = null;
        try{
           session = sessionFactory.openSession();
           //先去找到响应的部分，然后用transformer去重，然后返回一个list
           return session.createCriteria(Restaurant.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return new ArrayList<>(); //啥都没有就直接返回空list
    }

    public List<MenuItem> getAllMenuitem(int restaurantId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null) {
                return restaurant.getMenuItemList(); //直接返回所有饭店
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>(); //啥也没有直接返回
    }

    public MenuItem getMenuitem(int menuItemId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();

            return session.get(MenuItem.class, menuItemId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
