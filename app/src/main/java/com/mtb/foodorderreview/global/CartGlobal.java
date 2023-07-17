package com.mtb.foodorderreview.global;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int restaurantId = -1;
    private List<CartFood> foods = new ArrayList<>();
    private static Cart cart = new Cart();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public static Cart getInstance() {
        return cart;
    }


    private Cart() {
    }

    public void reset() {
        restaurantId = -1;
        foods = new ArrayList<>();
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<CartFood> getFoods() {
        return foods;
    }

    public void setFoods(List<CartFood> foods) {
        this.foods = foods;
    }
}
