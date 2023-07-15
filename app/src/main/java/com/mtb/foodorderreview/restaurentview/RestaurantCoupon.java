package com.mtb.foodorderreview.restaurentview;

public class RestaurantCoupon {
    private String title;
    private String code;
    private double discount;

    public RestaurantCoupon(String title, String code, double discount) {
        this.title = title;
        this.code = code;
        this.discount = discount;
    }


    public String getTitle() {
        return title;
    }

    public double getDiscount() {
        return discount;
    }

    public String getCode() {
        return code;
    }
}
