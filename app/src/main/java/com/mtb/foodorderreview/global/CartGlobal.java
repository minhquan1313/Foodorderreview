package com.mtb.foodorderreview.global;

import com.mtb.foodorderreview.homeview.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class CartGlobal {
    private Restaurant restaurant;
    private List<CartFood> foods = new ArrayList<>();
    private static CartGlobal cartGlobal = new CartGlobal();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public static CartGlobal getInstance() {
        return cartGlobal;
    }


    private CartGlobal() {
    }

    public void reset() {
        restaurant = null;
        foods = new ArrayList<>();
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addFood(CartFood food) {
        CartFood ff = null;

        for (CartFood f : foods) {
            if (f.getFood().getId() == food.getFood().getId())
                ff = f;
        }

        if (ff != null) {
            ff.setQuantity(ff.getQuantity() + food.getQuantity());
            return;
        }

        this.foods.add(food);
    }

    public List<CartFood> getFoods() {
        return foods;
    }
}
