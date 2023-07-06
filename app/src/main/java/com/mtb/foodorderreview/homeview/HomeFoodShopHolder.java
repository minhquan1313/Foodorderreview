package com.mtb.foodorderreview.homeview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;

public class HomeFoodShopHolder extends RecyclerView.ViewHolder {
    public TextView food_shop_name1;

    public HomeFoodShopHolder(@NonNull View itemView) {
        super(itemView);

        food_shop_name1 = itemView.findViewById(R.id.food_shop_name1);
    }
}
