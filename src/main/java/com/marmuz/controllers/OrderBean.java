package com.marmuz.controllers;

import com.marmuz.ejb.OrderEJB;
import com.marmuz.models.Coffee;
import com.marmuz.models.Order;
import org.primefaces.context.PrimeFacesContext;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.List;

@ManagedBean
@SessionScoped
public class OrderBean {

    private Long id;
    private double price;
    private int amount;
    private String delivery;
    private String timeForDelivery;
    private Coffee coffee;

    @EJB
    private OrderEJB orderEJB;

    @PostConstruct
    public void init() {
        this.orderEJB = new OrderEJB();
    }

    public List<Order> ordersList() {
        return orderEJB.getOrders();

    }


    public String add(Order order) {
        orderEJB.createOrUpdate(delivery, amount, timeForDelivery, coffee, id);
        id = null;
        return "main";
    }

    public String delete() {
        orderEJB.delete(id);
        id = null;
        return "main";
    }

    public String deleteOrder(Order order) {
        setId(order.getId());
        setCoffee(order.getCoffee());
        setAmount(order.getAmount());
        setDelivery(order.getDelivery());
        setTimeForDelivery(order.getTimeForDelivery());
        setPrice(order.getPrice());
        return "deleteOrder";
    }

    public String update(Order order) {
        setId(order.getId());
        setCoffee(order.getCoffee());
        setAmount(order.getAmount());
        setDelivery(order.getDelivery());
        setTimeForDelivery(order.getTimeForDelivery());
        return "newOrder";
    }


    public void validateDeliveryTime(UIComponent toValidate,FacesContext cont,Object o) throws ValidatorException {
        String time = (String) o;
        if (!time.matches("\\b(([0-1][0-9]|[2][0-4]):([0-5][0-9])-([0-1][0-9]|[2][0-4]):([0-5][0-9]))")) {
            FacesMessage msg = new FacesMessage("Неправильное время доставки");
            throw new ValidatorException(msg);
        }
        int fromHours = Integer.parseInt(time.substring(0, 2));
        int toHours = Integer.parseInt(time.substring(6, 8));

        if (fromHours > toHours) {
            FacesMessage msg = new FacesMessage("Неправильное время доставки");
            throw new ValidatorException(msg);
        }
    }

    public void validateAmount(UIComponent toValid, FacesContext cont, Object o) throws ValidatorException {
        int amount = (int) o;
        if (amount < 100) {
            FacesMessage msg = new FacesMessage("Колличество должно быть больше, чем 100 ");
            msg.setSeverity(FacesMessage.SEVERITY_INFO); //как выглядит окошко с сообщением
            PrimeFacesContext.getCurrentInstance().addMessage(null, msg);
            throw new ValidatorException(msg);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = coffee.getPrice();
        if (delivery.equals("Courier")) {
            this.price += 2;
        }
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getTimeForDelivery() {
        return timeForDelivery;
    }

    public void setTimeForDelivery(String timeForDelivery) {
        this.timeForDelivery = timeForDelivery;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public OrderEJB getOrderEJB() {
        return orderEJB;
    }

    public void setOrderEJB(OrderEJB orderEJB) {
        this.orderEJB = orderEJB;
    }
}
