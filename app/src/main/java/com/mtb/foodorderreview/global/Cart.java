package com.mtb.foodorderreview.global;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int restaurantId;
    private List<FoodInCart> foods = new ArrayList<>();

    private Cart cart;


    public Cart getInstance() {
        if (cart == null) cart = new Cart();

        return cart;
    }

    public List<FoodInCart> getFoods() {
        return foods;
    }
}
