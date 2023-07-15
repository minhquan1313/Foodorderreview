package com.mtb.foodorderreview.global;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int restaurantId = -1;
    private List<FoodInCart> foods = new ArrayList<>();


    public static Cart getInstance() {
        return cart;
    }

    private static Cart cart = new Cart();

    private Cart() {
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<FoodInCart> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodInCart> foods) {
        this.foods = foods;
    }
}
