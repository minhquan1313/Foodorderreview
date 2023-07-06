package com.mtb.foodorderreview.homeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;

import java.util.List;

public class HomeFoodShopAdapter extends RecyclerView.Adapter<HomeFoodShopHolder> {
    private List<String> list;
    private final LayoutInflater inflater;


    public HomeFoodShopAdapter(Context context, List<String> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HomeFoodShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_food_shop, null);
        return new HomeFoodShopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFoodShopHolder holder, int position) {
        holder.food_shop_name1.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
