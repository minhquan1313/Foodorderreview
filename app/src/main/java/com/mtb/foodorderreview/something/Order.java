package com.mtb.foodorderreview.something;

import com.mtb.foodorderreview.global.CartFood;
import com.mtb.foodorderreview.homeview.Restaurant;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Restaurant restaurant;
    private Date createdAt;
    private STATE state;
    private List<CartFood> cartFood;

    public Order(Restaurant restaurant, List<CartFood> cartFood) {
        this.restaurant = restaurant;
        this.createdAt = new Date();
        this.state = STATE.PENDING;
        this.cartFood = cartFood;
    }

    public Order(int id, Restaurant restaurant, Date createdAt, STATE state, List<CartFood> cartFood) {
        this.id = id;
        this.restaurant = restaurant;
        this.createdAt = createdAt;
        this.state = state;
        this.cartFood = cartFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public STATE getState() {
        return state;
    }

    public List<CartFood> getCartFood() {
        return cartFood;
    }

    public void setCartFood(List<CartFood> cartFood) {
        this.cartFood = cartFood;
    }

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public enum STATE {
        PENDING, DELIVERED;

        public int getValue() {
            switch (this) {
                case PENDING:
                    return 1;
                case DELIVERED:
                    return 2;
            }

            return 1;
        }
    }
}
