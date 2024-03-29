package com.laioffer.onlineorder.service;

import com.laioffer.onlineorder.dao.MenuInfoDao;
import com.laioffer.onlineorder.entity.MenuItem;
import com.laioffer.onlineorder.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuInfoService {

    @Autowired
    private MenuInfoDao menuInfoDao;

    public List<Restaurant> getRestaurants(){
        return menuInfoDao.getRestaurants();
    }

    public List<MenuItem> getAllMenuitem(int restaurantId) {
        return menuInfoDao.getAllMenuitem(restaurantId);
    }

    public MenuItem getMenuitem(int id){
        return menuInfoDao.getMenuitem(id);
    }
}
