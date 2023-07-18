package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.components.ExpandableHeightGridView;
import com.mtb.foodorderreview.global.CartGlobal;
import com.mtb.foodorderreview.global.RestaurantFoodGlobal;
import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.restaurentview.RestaurantCoupon;
import com.mtb.foodorderreview.restaurentview.RestaurantCouponRecyclerAdapter;
import com.mtb.foodorderreview.restaurentview.RestaurantFood;
import com.mtb.foodorderreview.restaurentview.RestaurantFoodGridAdapter;
import com.mtb.foodorderreview.utils.ItemClickListener;

import java.util.Arrays;

public class RestaurantActivity extends AppCompatActivity {
    TextView restaurant_name1,
            restaurant_detail1,
            restaurant_location1,
            restaurant_cart_quantity_text;
    ImageView restaurant_banner1;
    RelativeLayout restaurant_cart_btn;
    RestaurantCoupon coupon;
    LinearLayout linear_btn_restaurant_back1;
    CartGlobal cartGlobal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        initialization();

        updateCart();

        backBtn(linear_btn_restaurant_back1);
        cartBtn();

        couponsRecycler();
        foodGrid();
    }

    private void initialization() {
        restaurant_name1 = findViewById(R.id.restaurant_name1);
        restaurant_banner1 = findViewById(R.id.restaurant_banner1);
        restaurant_detail1 = findViewById(R.id.restaurant_detail1);
        restaurant_location1 = findViewById(R.id.restaurant_location1);
        restaurant_cart_btn = findViewById(R.id.restaurant_cart_btn);
        restaurant_cart_quantity_text = findViewById(R.id.restaurant_cart_quantity_text);
        linear_btn_restaurant_back1 = findViewById(R.id.linear_btn_restaurant_back1);

        cartGlobal = CartGlobal.getInstance();

        Restaurant restaurant = RestaurantGlobal.getInstance().getRestaurant();

        restaurant_name1.setText(restaurant.getName());
        restaurant_location1.setText(restaurant.getAddress());
        restaurant_banner1.setImageResource(restaurant.getImage());

        cartGlobal.reset();
        cartGlobal.setRestaurant(restaurant);
    }

    private void backBtn(LinearLayout btn) {
        btn.setOnClickListener(v -> finish());
    }

    private void cartBtn() {

        restaurant_cart_btn.setOnClickListener(v -> {
            // Checkout activity
            Toast.makeText(RestaurantActivity.this, "Cart click ne", Toast.LENGTH_SHORT).show();
        });
    }

    public void updateCart() {
        if (cartGlobal.getFoodList().size() == 0) {
            restaurant_cart_btn.setVisibility(View.INVISIBLE);
            return;
        }

        restaurant_cart_btn.setVisibility(View.VISIBLE);

        restaurant_cart_quantity_text.setText(String.valueOf(cartGlobal.getFoodList().size()));
    }

    private void couponsRecycler() {
        RestaurantCoupon[] l = {
                new RestaurantCoupon(1, "Giảm 40% luôn, lụm liền đi", "", 0.6),
                new RestaurantCoupon(2, "Giảm 10% nè", "", 0.1),
                new RestaurantCoupon(3, "Hihi Giảm 10% nè Giảm 10% nè Giảm 10% nè Giảm 10% nè Giảm 10% nè Giảm 10% nè", "", 0.1),
                new RestaurantCoupon(4, "d", "", 0.1),
                new RestaurantCoupon(5, "e", "", 0.1),
                new RestaurantCoupon(6, "f", "", 0.1),
        };

        final int[] couponPosition = {-1};
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RestaurantCouponRecyclerAdapter adapter = new RestaurantCouponRecyclerAdapter(this, Arrays.asList(l));
        adapter.setBtnClickListener(new ItemClickListener<RestaurantCoupon>() {
            @Override
            public void onClick(View view, int position, boolean isLongClick, RestaurantCoupon item) {
                if (!isLongClick) {
                    cartGlobal.setCoupon(item);

                    if (couponPosition[0] != -1)
                        adapter.notifyItemChanged(couponPosition[0]);

                    adapter.notifyItemChanged(position);
                    couponPosition[0] = position;

                    Toast.makeText(RestaurantActivity.this, "Btn coupon click " + item.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        RecyclerView recyclerView = findViewById(R.id.restaurant_coupon_recycler_1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void foodGrid() {
        RestaurantFood[] l = {
                new RestaurantFood(1, "Bún bò 1", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        13000),
                new RestaurantFood(2, "Bún bò 2", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        14000),
                new RestaurantFood(3, "Bún bò 3", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        15000),
                new RestaurantFood(4, "Bún bò 4", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        16000),
                new RestaurantFood(5, "Bún bò 5", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        17000),
                new RestaurantFood(6, "Bún bò 6", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        18000),
        };

        RestaurantFoodGridAdapter adapter = new RestaurantFoodGridAdapter(this, Arrays.asList(l));

        ExpandableHeightGridView gridView = findViewById(R.id.restaurant_food_grid);
        gridView.setAdapter(adapter);

        gridView.setExpanded(true);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            RestaurantFood f = (RestaurantFood) gridView.getItemAtPosition(position);
//            RestaurantGlobal.getInstance().setRestaurant();

            RestaurantFoodGlobal.getInstance().setFood(f);
            Intent intent = new Intent(this, FoodSelectActivity.class);

            startActivityIfNeeded(intent, 2);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            updateCart();
        }
    }
}