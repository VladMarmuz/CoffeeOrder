package com.marmuz.ejb;

import com.marmuz.models.Coffee;
import com.marmuz.models.Order;
import com.marmuz.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class OrderEJB {

    public void delete(Long id) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Order order = session.find(Order.class, id);
            session.delete(order);
            transaction.commit();
        }
    }

    public List<Order> getOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Order");
            return query.getResultList();
        }
    }

    public void createOrUpdate(String delivery, int amount, String timeForDelivery, Coffee coffee, Long id) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Order order = new Order();
            order.setAmount(amount);
            order.setCoffee(coffee);
            order.setDelivery(delivery);
            order.setTimeForDelivery(timeForDelivery);
            double price = amount * coffee.getPrice() / 100;
            if (delivery.equals("Courier")) {
                price += 2;
            }
            order.setPrice(price);
            session.saveOrUpdate(order);
            transaction.commit();
        }
    }


}
