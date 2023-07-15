package com.mtb.foodorderreview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.components.ExpandableHeightGridView;
import com.mtb.foodorderreview.global.Cart;
import com.mtb.foodorderreview.restaurentview.RestaurantCoupon;
import com.mtb.foodorderreview.restaurentview.RestaurantCouponRecyclerAdapter;
import com.mtb.foodorderreview.restaurentview.RestaurantFood;
import com.mtb.foodorderreview.restaurentview.RestaurantFoodGridAdapter;

import java.util.Arrays;

public class RestaurantActivity extends AppCompatActivity {
    TextView restaurant_name1,
            restaurant_detail1,
            restaurant_location1;
    ImageView restaurant_banner1;
    CardView restaurant_cart;
    RestaurantCoupon coupon;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        initialization();

        bundlePart();
        backBtn();
        cartBtn();

        couponsRecycler();
        foodGrid();
    }


    private void initialization() {
        restaurant_name1 = findViewById(R.id.restaurant_name1);
        restaurant_banner1 = findViewById(R.id.restaurant_banner1);
        restaurant_detail1 = findViewById(R.id.restaurant_detail1);
        restaurant_location1 = findViewById(R.id.restaurant_location1);
        restaurant_cart = findViewById(R.id.restaurant_cart);

        cart = Cart.getInstance();

    }

    private void bundlePart() {
        Bundle bundle = getIntent().getExtras();

        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String description = bundle.getString("description");
        int image = bundle.getInt("image");

        restaurant_name1.setText(name);
        restaurant_banner1.setImageResource(image);

    }


    private void backBtn() {
        LinearLayout linear_btn_restaurant_back1 = findViewById(R.id.linear_btn_restaurant_back1);

        linear_btn_restaurant_back1.setOnClickListener(v -> finish());
    }

    private void cartBtn() {
        if (cart.getRestaurantId() == -1)
            restaurant_cart.setVisibility(View.INVISIBLE);

        restaurant_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checkout activity
            }
        });
    }

    public void updateCart() {
        if (cart.getRestaurantId() != -1)
            restaurant_cart.setVisibility(View.VISIBLE);
    }

    private void couponsRecycler() {
        RestaurantCoupon[] l = {
                new RestaurantCoupon("Giảm 40% luôn, lụm liền đi", "", 0.6),
                new RestaurantCoupon("Giảm 10% nè", "", 0.1),
                new RestaurantCoupon("Hihi Giảm 10% nè Giảm 10% nè Giảm 10% nè Giảm 10% nè Giảm 10% nè Giảm 10% nè", "",
                        0.1),
                new RestaurantCoupon("d", "", 0.1),
                new RestaurantCoupon("e", "", 0.1),
                new RestaurantCoupon("f", "", 0.1),
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RestaurantCouponRecyclerAdapter adapter = new RestaurantCouponRecyclerAdapter(this, Arrays.asList(l));

        RecyclerView recyclerView = findViewById(R.id.restaurant_coupon_recycler_1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void foodGrid() {
        RestaurantFood[] l = {
                new RestaurantFood(1, "Bún bò 1", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        12000),
                new RestaurantFood(2, "Bún bò 2", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        12000),
                new RestaurantFood(3, "Bún bò 3", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        12000),
                new RestaurantFood(4, "Bún bò 4", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        12000),
                new RestaurantFood(5, "Bún bò 5", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        12000),
                new RestaurantFood(6, "Bún bò 6", "Bún bò thơm ngon mời ban ăn nha", R.drawable.img_sample_food_2,
                        12000),
        };

        RestaurantFoodGridAdapter adapter = new RestaurantFoodGridAdapter(this, Arrays.asList(l));

        ExpandableHeightGridView gridView = findViewById(R.id.restaurant_food_grid);
        gridView.setAdapter(adapter);

        gridView.setExpanded(true);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            RestaurantFood f = (RestaurantFood) gridView.getItemAtPosition(position);
            Toast.makeText(this, "Selected :" + " " + f.getName(), Toast.LENGTH_LONG).show();
        });
    }

}