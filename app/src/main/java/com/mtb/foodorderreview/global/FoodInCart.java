package com.mtb.foodorderreview.global;

import com.mtb.foodorderreview.restaurentview.RestaurantFood;

public class FoodInCart {
    RestaurantFood food;
    int quantity;

    public FoodInCart(RestaurantFood food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public RestaurantFood getFood() {
        return food;
    }

    public void setFood(RestaurantFood food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
