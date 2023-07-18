package com.mtb.foodorderreview.global;

import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.restaurentview.RestaurantCoupon;

import java.util.ArrayList;
import java.util.List;

public class CartGlobal {
    private Restaurant restaurant;
    private List<CartFood> foodList = new ArrayList<>();
    private RestaurantCoupon coupon;
    //    private List<RestaurantCoupon> couponList = new ArrayList<>();
    private static final CartGlobal instance = new CartGlobal();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public static CartGlobal getInstance() {
        return instance;
    }


    private CartGlobal() {
    }

    public void reset() {
        restaurant = null;
        coupon = null;
        foodList = new ArrayList<>();
//        couponList = new ArrayList<>();
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public CartFood findCartFoodByFoodId(int id) {
        for (CartFood f : foodList)
            if (f.getFood().getId() == id) {
                return f;
            }

        return null;
    }

    public void addFood(CartFood food) {
        CartFood ff = this.findCartFoodByFoodId(food.getFood().getId());

        if (ff != null) {
            ff.setQuantity(ff.getQuantity() + food.getQuantity());
            return;
        }

        this.foodList.add(food);
    }

//    public RestaurantCoupon findCouponById(int id) {
//        for (RestaurantCoupon f : couponList)
//            if (f.getId() == id) {
//                return f;
//            }
//
//        return null;
//    }

//    public void addCoupon(RestaurantCoupon coupon) {
//        RestaurantCoupon ff = this.findCouponById(coupon.getId());
//
//        if (ff == null) this.couponList.add(coupon);
//    }


    public RestaurantCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(RestaurantCoupon coupon) {
        this.coupon = coupon;
    }

    public List<CartFood> getFoodList() {
        return foodList;
    }

}
