package com.mtb.foodorderreview.restaurentview;

public class RestaurantCoupon {
    private String title;
    private String code;

    public RestaurantCoupon(String detail, String code) {
        this.title = detail;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }
}
