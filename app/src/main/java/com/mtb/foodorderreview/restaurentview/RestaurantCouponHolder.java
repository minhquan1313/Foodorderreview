package com.mtb.foodorderreview.restaurentview;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;

public class RestaurantCouponHolder extends RecyclerView.ViewHolder {
    private String item;
    private TextView restaurant_coupon_detail1;
    private Button restaurant_coupon_claim_btn1;

    public RestaurantCouponHolder(@NonNull View itemView) {
        super(itemView);

        restaurant_coupon_detail1 = itemView.findViewById(R.id.restaurant_coupon_detail1);
        restaurant_coupon_claim_btn1 = itemView.findViewById(R.id.restaurant_coupon_claim_btn1);
    }

    public TextView getRestaurant_coupon_detail1() {
        return restaurant_coupon_detail1;
    }

    public Button getRestaurant_coupon_claim_btn1() {
        return restaurant_coupon_claim_btn1;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
