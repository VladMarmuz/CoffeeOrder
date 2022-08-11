package com.marmuz.models;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "typeForDelivery")
    private String timeForDelivery;
    @Column(name = "delivery")
    private String delivery;
    @Column(name = "price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "coffee_id", nullable = false)
    private Coffee coffee;


    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getId() != order.getId()) return false;
        if (getAmount() != order.getAmount()) return false;
        if (Double.compare(order.getPrice(), getPrice()) != 0) return false;
        if (!getTimeForDelivery().equals(order.getTimeForDelivery())) return false;
        if (!getDelivery().equals(order.getDelivery())) return false;
        return getCoffee().equals(order.getCoffee());
    }
}
