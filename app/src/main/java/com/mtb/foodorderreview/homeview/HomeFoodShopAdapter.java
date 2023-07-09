package com.mtb.foodorderreview.homeview;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;

import java.util.List;

public class HomeFoodShopAdapter extends RecyclerView.Adapter<HomeFoodShopHolder> {
    private List<HomeFood> list;
    private final LayoutInflater inflater;
    private final Resources resources;

    private HomeFoodClickListener clickListener;


    public HomeFoodShopAdapter(Context context, List<HomeFood> list, Resources resources) {
        this.list = list;
        this.resources = resources;
        this.inflater = LayoutInflater.from(context);
    }

    public void setClickListener(HomeFoodClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public HomeFoodShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_restaurant_item, null);
        return new HomeFoodShopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFoodShopHolder holder, int position) {
        HomeFood item = list.get(position);

        holder.getFood_shop_name1().setText(item.getName());
        holder.getFood_shop_image1().setImageResource(item.getImage());
        
        holder.setClickListener(clickListener);
        holder.setItem(item);


        if (position + 1 == list.size()) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
            holder.getFood_shop_cardView1().setLayoutParams(params);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
