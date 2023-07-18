package com.mtb.foodorderreview.restaurentview;

public class RestaurantCoupon {
    private int id;
    private String title;
    private String code;
    private double discount;

    public RestaurantCoupon(int id, String title, String code, double discount) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.discount = discount;
    }

    public int getId() {
        return id;
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
