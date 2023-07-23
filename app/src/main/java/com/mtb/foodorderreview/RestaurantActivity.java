package com.mtb.foodorderreview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.api.FoodService;
import com.mtb.foodorderreview.components.ExpandableHeightGridView;
import com.mtb.foodorderreview.global.CartGlobal;
import com.mtb.foodorderreview.global.RestaurantFoodGlobal;
import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.homeview.RestaurantRecyclerAdapter;
import com.mtb.foodorderreview.model.Food;
import com.mtb.foodorderreview.model.LoaiFood;
import com.mtb.foodorderreview.model.NhaHang;
import com.mtb.foodorderreview.restaurentview.RestaurantCoupon;
import com.mtb.foodorderreview.restaurentview.RestaurantCouponRecyclerAdapter;
import com.mtb.foodorderreview.restaurentview.RestaurantFood;
import com.mtb.foodorderreview.restaurentview.RestaurantFoodGridAdapter;
import com.mtb.foodorderreview.utils.IClickListener;
import com.mtb.foodorderreview.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        restaurant_banner1.setImageResource(restaurant.getImage());

//        if (cartGlobal.getRestaurant() != restaurant) {
//            cartGlobal.reset();
//            cartGlobal.setRestaurant(restaurant);
//        }
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
        RestaurantCoupon[] l = {
                new RestaurantCoupon(1, "Giảm 40% luôn, lụm liền đi", "", 0.6, RestaurantCoupon.DiscountType.PERCENT),
                new RestaurantCoupon(2, "Giảm 10% nè", "", 0.1, RestaurantCoupon.DiscountType.PERCENT),
                new RestaurantCoupon(3, "Hihi Giảm 10% nè. Giảm 10% nè. Giảm 10% nè.", "", 0.1, RestaurantCoupon.DiscountType.PERCENT),
                new RestaurantCoupon(4, "Giam 15k", "", 15000, RestaurantCoupon.DiscountType.FIXED),
                new RestaurantCoupon(5, "Giam 20k", "", 20000, RestaurantCoupon.DiscountType.FIXED),
                new RestaurantCoupon(6, "Giam 10%", "", 0.1, RestaurantCoupon.DiscountType.PERCENT),
        };

        final int[] couponPosition = {-1};
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RestaurantCouponRecyclerAdapter adapter = new RestaurantCouponRecyclerAdapter(this, Arrays.asList(l));
        adapter.setBtnClickListener(new IClickListener<RestaurantCoupon>() {
            @Override
            public void onClick(View view, int position, boolean isLongClick, RestaurantCoupon item) {
                if (!isLongClick) {
                    cartGlobal.setCoupon(item);

                    if (couponPosition[0] != -1)
                        adapter.notifyItemChanged(couponPosition[0]);

                    adapter.notifyItemChanged(position);
                    couponPosition[0] = position;
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.restaurant_coupon_recycler_1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void foodGrid() {
        int idNhaHang = getIntent().getExtras().getInt("id");
        List<RestaurantFood> list = new ArrayList<>();
        RestaurantFoodGridAdapter adapter = new RestaurantFoodGridAdapter(this,list);
        FoodService.apiService.getListFoodByNhaHang(idNhaHang).enqueue(new Callback<List<Food>>() {


            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                List<Food> listFood = response.body();

                for (Food l : listFood) {
                    list.add(new RestaurantFood(l.getId(), l.getTen(), "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                            l.getGiaTien().intValue()));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });

        ExpandableHeightGridView gridView = findViewById(R.id.restaurant_food_grid);
        gridView.setAdapter(adapter);

        gridView.setExpanded(true);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            RestaurantFood f = (RestaurantFood) gridView.getItemAtPosition(position);
            RestaurantFoodGlobal.getInstance().setFood(f);

            Intent intent = new Intent(this, FoodSelectActivity.class);
            startActivityIfNeeded(intent, 2);
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
                if (resultCode != Activity.RESULT_OK) return;
                updateCartUI();
                break;

            // Checkout ok
            case 3:
                if (resultCode != Activity.RESULT_OK) return;
                Intent intent = new Intent(this, DeliveryActivity.class);
                startActivity(intent);

                finish();
                break;
        }
    }
}