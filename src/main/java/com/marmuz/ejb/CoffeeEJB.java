package com.marmuz.ejb;

import com.marmuz.models.Coffee;
import com.marmuz.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
public class CoffeeEJB {

    public void create(String coffeeType,double price){

        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           transaction = session.beginTransaction();
            Coffee coffee = new Coffee();
            coffee.setPrice(price);
            coffee.setCoffeeType(coffeeType);
            session.save(coffee);
            transaction.commit();
        }
    }

    public List<Coffee> coffeeList() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select coffee from Coffee coffee");
            return query.getResultList();
        }
    }

    public List<Long> coffeeListNames() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select coffee from Coffee coffee");
            List<Coffee> coffees = query.getResultList();
            return coffees.stream().map(Coffee::getId).collect(Collectors.toList());
        }
    }

    public Coffee getByType(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Coffee.class, id);
        }
    }

}
