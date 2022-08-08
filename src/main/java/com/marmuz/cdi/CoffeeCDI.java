package com.marmuz.cdi;

import com.marmuz.ejb.CoffeeEJB;
import com.marmuz.models.Coffee;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class CoffeeCDI implements Serializable {

    private long id;
    private String coffeeType;
    private double price;
    private List<Coffee> allCoffee;

    @EJB
    private CoffeeEJB coffeeEJB;

    @PostConstruct
    public void init() {
        this.coffeeEJB = new CoffeeEJB();
        this.allCoffee =coffeeEJB.coffeeList();
    }

    public void add(){
        coffeeEJB.create(coffeeType,price);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Coffee> getAllCoffee() {
        return allCoffee;
    }

    public void setAllCoffee(List<Coffee> allCoffee) {
        this.allCoffee = allCoffee;
    }

    public CoffeeEJB getCoffeeEJB() {
        return coffeeEJB;
    }

    public void setCoffeeEJB(CoffeeEJB coffeeEJB) {
        this.coffeeEJB = coffeeEJB;
    }
}
