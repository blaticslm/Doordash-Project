package com.laioffer.onlineorder.dao; //Data Access Object: 说明是访问数据的文件区

import com.laioffer.onlineorder.entity.Authorities;
import com.laioffer.onlineorder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//数据库交换
@Repository //从Spring上来看，和Component是一个性质，只不过语义是和数据库交换的罢了
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmail(customer.getEmail());

        Session session = null;

        try{ //打开session
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities); //给数据库的表
            session.save(customer);
            session.getTransaction().commit(); //写数据库的动作

        } catch(Exception exception){
            exception.printStackTrace(); //打错误
            if(session != null) {
                session.getTransaction().rollback();
            }
        } finally { //加入完毕
            if(session != null) {
                session.close();
            }
        }

    }

    public Customer getCustomer(String email) { //从数据库里读customer
        Customer customer = null;
        try (Session session = sessionFactory.openSession()) {
            customer = session.get(Customer.class, email);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }

}



