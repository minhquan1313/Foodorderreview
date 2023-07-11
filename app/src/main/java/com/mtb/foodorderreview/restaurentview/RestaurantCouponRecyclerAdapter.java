package com.mtb.foodorderreview.restaurentview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;

import java.util.List;

public class RestaurantCouponRecyclerAdapter extends RecyclerView.Adapter<RestaurantCouponHolder> {
    private List<String> list;
    private final LayoutInflater inflater;

    public RestaurantCouponRecyclerAdapter(Context context, List<String> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RestaurantCouponHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.restaurant_coupon, null);
        return new RestaurantCouponHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantCouponHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
