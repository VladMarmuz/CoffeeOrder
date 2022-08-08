package com.marmuz.models;
import javax.persistence.*;
@Entity
@Table(name = "order")
public class Order{

    @Id
    @GeneratedValue
    private long id;
    private int amount;
    private String timeForDelivery;
    private String delivery;
    private double price;
    @ManyToOne
    @JoinColumn(name = "coffee_id", nullable = false)
    private Coffee coffee;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTimeForDelivery() {
        return timeForDelivery;
    }

    public void setTimeForDelivery(String timeForDelivery) {
        this.timeForDelivery = timeForDelivery;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public Order() {
    }

    public Order(long id, int amount, String timeForDelivery, String delivery, double price, Coffee coffee) {
        this.id = id;
        this.amount = amount;
        this.timeForDelivery = timeForDelivery;
        this.delivery = delivery;
        this.price = price;
        this.coffee = coffee;
    }
}
