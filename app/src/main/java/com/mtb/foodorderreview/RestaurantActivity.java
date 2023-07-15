package com.mtb.foodorderreview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.restaurentview.RestaurantCoupon;
import com.mtb.foodorderreview.restaurentview.RestaurantCouponRecyclerAdapter;

import java.util.Arrays;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        BundlePart();
        Miscellaneous();
        Coupons();
    }


    private void BundlePart() {
        Bundle bundle = getIntent().getExtras();

        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        int image = bundle.getInt("image");

        TextView restaurant_name1 = findViewById(R.id.restaurant_name1);
        ImageView restaurant_banner1 = findViewById(R.id.restaurant_banner1);
        restaurant_name1.setText(name);
        restaurant_banner1.setImageResource(image);

    }

    private void Miscellaneous() {
        LinearLayout linear_btn_restaurant_back1 = findViewById(R.id.linear_btn_restaurant_back1);

        linear_btn_restaurant_back1.setOnClickListener(v -> finish());
    }

    private void Coupons() {
        RestaurantCoupon[] l = {
                new RestaurantCoupon("Giảm 40% luôn, lụm liền đi", ""),
                new RestaurantCoupon("Giảm 10% nè", ""),
                new RestaurantCoupon("Hihi Giảm 10% nè Giảm 10% nè Giảm 10% nè Giảm 10% nè Giảm 10% nè Giảm 10% nè",
                        ""),
                new RestaurantCoupon("d", ""),
                new RestaurantCoupon("e", ""),
                new RestaurantCoupon("f", ""),
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RestaurantCouponRecyclerAdapter adapter = new RestaurantCouponRecyclerAdapter(this, Arrays.asList(l));

        // adapter.setClickListener((view1, position, isLongClick, homeFood) -> {
        // if (!isLongClick) {
        // Intent intent = new Intent(context, RestaurantActivity.class);
        // intent.putExtra("id", homeFood.getId());
        // intent.putExtra("name", homeFood.getName());
        // intent.putExtra("image", homeFood.getImage());
        // startActivity(intent);
        // } else {
        // // Do something if it's long click
        // }
        // });

        RecyclerView recyclerView = findViewById(R.id.restaurant_coupon_recycler_1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}