package com.mtb.foodorderreview.homeview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;

public class HomeFoodShopHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private HomeFood item;
    private TextView food_shop_name1;
    private CardView food_shop_cardView1;
    private ImageView food_shop_image1;
    private HomeFoodClickListener clickListener;


    public HomeFoodShopHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        food_shop_name1 = itemView.findViewById(R.id.food_shop_name1);
        food_shop_cardView1 = itemView.findViewById(R.id.food_shop_cardView1);
        food_shop_image1 = itemView.findViewById(R.id.food_shop_image1);
    }

    public void setClickListener(HomeFoodClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public TextView getFood_shop_name1() {
        return food_shop_name1;
    }

    public CardView getFood_shop_cardView1() {
        return food_shop_cardView1;
    }

    public ImageView getFood_shop_image1() {
        return food_shop_image1;
    }

    public HomeFood getItem() {
        return item;
    }

    public void setItem(HomeFood item) {
        this.item = item;
    }

    @Override
    public void onClick(View v) {
        clickListener.onClick(v, getAdapterPosition(), false, item);


//        Toast.makeText(v.getContext(), String.valueOf(item.getId()) + " " + item.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
