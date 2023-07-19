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

import com.mtb.foodorderreview.api.FoodService;
import com.mtb.foodorderreview.api.NhaHangService;
import com.mtb.foodorderreview.api.UuDaiService;
import com.mtb.foodorderreview.components.ExpandableHeightGridView;
import com.mtb.foodorderreview.global.CartGlobal;
import com.mtb.foodorderreview.global.RestaurantFoodGlobal;
import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.homeview.RestaurantRecyclerAdapter;
import com.mtb.foodorderreview.model.Food;
import com.mtb.foodorderreview.model.LoaiFood;
import com.mtb.foodorderreview.model.NhaHang;
import com.mtb.foodorderreview.model.UuDai;
import com.mtb.foodorderreview.restaurentview.RestaurantCoupon;
import com.mtb.foodorderreview.restaurentview.RestaurantCouponRecyclerAdapter;
import com.mtb.foodorderreview.restaurentview.RestaurantFood;
import com.mtb.foodorderreview.restaurentview.RestaurantFoodGridAdapter;
import com.mtb.foodorderreview.utils.ItemClickListener;

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
            restaurant_cart_quantity_text,
            rate,
            countDanhGia;
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
        rate = findViewById(R.id.rate);
        countDanhGia = findViewById(R.id.count_danh_gia);
        cartGlobal = CartGlobal.getInstance();

        Restaurant restaurant = RestaurantGlobal.getInstance().getRestaurant();

        restaurant_name1.setText(restaurant.getName());
        restaurant_location1.setText(restaurant.getAddress());
        restaurant_banner1.setImageResource(restaurant.getImage());

        cartGlobal.reset();
        cartGlobal.setRestaurant(restaurant);


        //Tải lượng đánh giá trung bình của 1 nhà hàng
        putRatingOfRestaurant();
    }

    private void putRatingOfRestaurant(){
        int idNhaHang = getIntent().getExtras().getInt("id");
        NhaHangService.apiService.getRatingOfRestaurant(idNhaHang).enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                rate.setText(response.body().toString());
            }
            @Override
            public void onFailure(Call<Double> call, Throwable t) {
            }
        });
        NhaHangService.apiService.getCountOfRate(idNhaHang).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                countDanhGia.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
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
        int idNhaHang = getIntent().getExtras().getInt("id");
        List<RestaurantCoupon> l = new ArrayList<>();

//

        final int[] couponPosition = {-1};
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RestaurantCouponRecyclerAdapter adapter = new RestaurantCouponRecyclerAdapter(this, l);

        UuDaiService.apiService.getListUuDaiByNhaHang(idNhaHang).enqueue(new Callback<List<UuDai>>() {
            @Override
            public void onResponse(Call<List<UuDai>> call, Response<List<UuDai>> response) {
                List<UuDai> uuDaiList = response.body();
                for(UuDai u : uuDaiList){
                    l.add(new RestaurantCoupon(u.getId(), u.getNoiDung(), u.getId().toString(), u.getGiaTri()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<UuDai>> call, Throwable t) {

            }
        });


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