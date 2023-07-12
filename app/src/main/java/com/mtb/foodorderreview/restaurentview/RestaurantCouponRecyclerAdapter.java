package com.mtb.foodorderreview.restaurentview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;

import java.util.List;

public class RestaurantCouponRecyclerAdapter extends RecyclerView.Adapter<RestaurantCouponHolder> {
    private List<RestaurantCoupon> list;
    private final LayoutInflater inflater;

    public RestaurantCouponRecyclerAdapter(Context context, List<RestaurantCoupon> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RestaurantCouponHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.restaurant_coupon_adapter, null);
        return new RestaurantCouponHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantCouponHolder holder, int position) {
        RestaurantCoupon item = list.get(position);

        holder.getRestaurant_coupon_detail1().setText(item.getTitle());

        if (position > 0) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.getRestaurant_coupon_layout1().getLayoutParams();
            lp.leftMargin = 0;
            holder.getRestaurant_coupon_layout1().setLayoutParams(lp);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
