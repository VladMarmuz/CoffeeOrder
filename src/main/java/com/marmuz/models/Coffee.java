package com.marmuz.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coffee")
public class Coffee {

    @Id
    @GeneratedValue
    private long id;
    private String coffeeType;

    private double price;

    @OneToMany(mappedBy = "coffee")
    private List<Order> orders;


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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Coffee(long id, String coffeeType, double price, List<Order> orders) {
        this.id = id;
        this.coffeeType = coffeeType;
        this.price = price;
        this.orders = orders;
    }

    public Coffee() {
    }
}
