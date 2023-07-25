package com.mtb.foodorderreview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.mtb.foodorderreview.utils.IClickListener;
import com.mtb.foodorderreview.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {
    TextView restaurant_name1,
            restaurant_detail1,
            restaurant_location1,
            restaurant_cart_quantity_text;
    ImageView restaurant_banner1;
    RelativeLayout restaurant_cart_btn;
    LinearLayout linear_btn_restaurant_back1;
    CartGlobal cartGlobal;
    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        initialization();

        updateCartUI();

        Utils.UI.backBtn(this, linear_btn_restaurant_back1);

        couponsRecycler();
        foodGrid();

        cartBtn();
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

        restaurant = RestaurantGlobal.getInstance().getRestaurant();

        restaurant_name1.setText(restaurant.getName());
        restaurant_location1.setText(restaurant.getAddress());
        Utils.UI.setSrc(restaurant.getImage(), restaurant_banner1);
    }

    public void updateCartUI() {
        if (cartGlobal.getFoodList().size() == 0 || cartGlobal.getRestaurant().getId() != restaurant.getId()) {
            restaurant_cart_btn.setVisibility(View.INVISIBLE);
            return;
        }

        restaurant_cart_btn.setVisibility(View.VISIBLE);

        restaurant_cart_quantity_text.setText(String.valueOf(cartGlobal.getFoodList().size()));
    }

    private void couponsRecycler() {
        List<RestaurantCoupon> l = new ArrayList<RestaurantCoupon>() {
            {
                add(new RestaurantCoupon(1, "Giảm 40% luôn, lụm liền đi", "", 0.6, RestaurantCoupon.DiscountType.PERCENT));
                add(new RestaurantCoupon(2, "Giảm 10% nè", "", 0.1, RestaurantCoupon.DiscountType.PERCENT));
                add(new RestaurantCoupon(3, "Hihi Giảm 10% nè. Giảm 10% nè. Giảm 10% nè.", "", 0.1, RestaurantCoupon.DiscountType.PERCENT));
                add(new RestaurantCoupon(4, "Giam 15k", "", 15000, RestaurantCoupon.DiscountType.FIXED));
                add(new RestaurantCoupon(5, "Giam 20k", "", 20000, RestaurantCoupon.DiscountType.FIXED));
                add(new RestaurantCoupon(6, "Giam 10%", "", 0.1, RestaurantCoupon.DiscountType.PERCENT));
            }
        };

        // Find position of selected coupon on start
        RestaurantCoupon coupon = CartGlobal.getInstance().getCoupon();
        int pos = -1;
        if (coupon != null) {
            for (RestaurantCoupon c : l) {
                if (c.getId() == coupon.getId()) pos = l.indexOf(c);
            }
        }

        int[] couponPostRef = {pos};

        Toast.makeText(this, "" + couponPostRef[0], Toast.LENGTH_SHORT).show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.restaurant_coupon_recycler_1);
        RestaurantCouponRecyclerAdapter adapter = new RestaurantCouponRecyclerAdapter(this, l);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setBtnClickListener(new IClickListener<RestaurantCoupon>() {
            @Override
            public void onClick(View view, int position, boolean isLongClick, RestaurantCoupon item) {
                if (!isLongClick) {
                    cartGlobal.setCoupon(item);

                    if (couponPostRef[0] != -1)
                        adapter.notifyItemChanged(couponPostRef[0]);

                    adapter.notifyItemChanged(position);
                    couponPostRef[0] = position;
                }
            }
        });
    }

    private void foodGrid() {
        RestaurantFood[] l = {
                new RestaurantFood(1, "Bún bò 1", "Bún bò thơm ngon mời ban ăn nha", Utils.URL_SAMPLE_IMAGE,
                        13000),
                new RestaurantFood(2, "Bún bò 2", "Bún bò thơm ngon mời ban ăn nha", Utils.URL_SAMPLE_IMAGE,
                        14000),
                new RestaurantFood(3, "Bún bò 3", "Bún bò thơm ngon mời ban ăn nha", Utils.URL_SAMPLE_IMAGE,
                        15000),
                new RestaurantFood(4, "Bún bò 4", "Bún bò thơm ngon mời ban ăn nha", Utils.URL_SAMPLE_IMAGE,
                        16000),
                new RestaurantFood(5, "Bún bò 5", "Bún bò thơm ngon mời ban ăn nha", Utils.URL_SAMPLE_IMAGE,
                        17000),
                new RestaurantFood(6, "Bún bò 6", "Bún bò thơm ngon mời ban ăn nha", Utils.URL_SAMPLE_IMAGE,
                        18000),
        };

        RestaurantFoodGridAdapter adapter = new RestaurantFoodGridAdapter(this, Arrays.asList(l));

        ExpandableHeightGridView gridView = findViewById(R.id.restaurant_food_grid);
        gridView.setAdapter(adapter);

        gridView.setExpanded(true);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {
                RestaurantFood f = (RestaurantFood) gridView.getItemAtPosition(position);
                RestaurantFoodGlobal.getInstance().setFood(f);

                Intent intent = new Intent(RestaurantActivity.this, FoodSelectActivity.class);
                RestaurantActivity.this.startActivityIfNeeded(intent, 2);
            }
        });
    }

    private void cartBtn() {

        restaurant_cart_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartCheckoutActivity.class);
            startActivityIfNeeded(intent, 3);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            // Add food to cart
            case 2:
                if (resultCode != Activity.RESULT_OK)
                    return;
                updateCartUI();
                break;

            // Checkout ok
            case 3:
                if (resultCode != Activity.RESULT_OK)
                    return;
                Intent intent = new Intent(this, DeliveryActivity.class);
                startActivity(intent);

                finish();
                break;
        }
    }
}