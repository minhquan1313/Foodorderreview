package com.mtb.foodorderreview.homeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;
import com.mtb.foodorderreview.utils.ItemClickListener;

import java.util.List;

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantHolder> {
    private List<Restaurant> list;
    private final LayoutInflater inflater;
    private ItemClickListener<Restaurant> clickListener;

    public RestaurantRecyclerAdapter(Context context, List<Restaurant> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public void setClickListener(ItemClickListener<Restaurant> clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_home_restaurant, null);
        return new RestaurantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
        Restaurant item = list.get(position);

        holder.getFood_shop_name1().setText(item.getName());
        holder.getFood_shop_image1().setImageResource(item.getImage());

        holder.setClickListener(clickListener);
        holder.setItem(item);

        if (position > 0) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.getFood_shop_cardView1().getLayoutParams();
            lp.leftMargin = 0;
            holder.getFood_shop_cardView1().setLayoutParams(lp);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
