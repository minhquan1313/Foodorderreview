package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.global.CartFood;
import com.mtb.foodorderreview.global.CartGlobal;
import com.mtb.foodorderreview.global.RestaurantFoodGlobal;
import com.mtb.foodorderreview.restaurentview.RestaurantFood;
import com.mtb.foodorderreview.utils.Utils;

import java.util.Locale;

public class FoodSelectActivity extends AppCompatActivity {
    TextView food_select_name,
            food_select_price,
            food_select_description,
            food_select_quantity_text;
    ImageView food_select_banner;
    LinearLayout food_select_back_btn,
            food_select_search_btn,
            food_select_decrease_btn,
            food_select_increase_btn,
            food_select_share_btn;
    Button food_select_add_to_cart_btn;
    RestaurantFood food;
    int countQuantity = 1;
    final String ADD_TO_CART_STR = "Thêm - ";
    CartGlobal cartGlobal;

    enum Mode {
        INC,
        DES
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_select);
        initialization();
        updateQuantityUi();
        onQuantityChangeBtns();
        backBtn(food_select_back_btn);
        addToCartBtn();
    }

    private void initialization() {
        food_select_name = findViewById(R.id.food_select_name);
        food_select_price = findViewById(R.id.food_select_price);
        food_select_description = findViewById(R.id.food_select_description);
        food_select_quantity_text = findViewById(R.id.food_select_quantity_text);
        food_select_banner = findViewById(R.id.food_select_banner);
        food_select_back_btn = findViewById(R.id.food_select_back_btn);
        food_select_search_btn = findViewById(R.id.food_select_search_btn);
        food_select_decrease_btn = findViewById(R.id.food_select_decrease_btn);
        food_select_increase_btn = findViewById(R.id.food_select_increase_btn);
        food_select_share_btn = findViewById(R.id.food_select_share_btn);
        food_select_add_to_cart_btn = findViewById(R.id.food_select_add_to_cart_btn);

        food = RestaurantFoodGlobal.getInstance().getFood();

        food_select_name.setText(food.getName());
        food_select_price.setText(String.format("%,d" + Utils.CURRENCY, food.getPrice()));
        food_select_banner.setImageResource(food.getImage());
        food_select_description.setText(food.getDescription());

        cartGlobal = CartGlobal.getInstance();
    }

    private void onQuantityChangeBtns() {
        onQuantityChangeBtn(food_select_decrease_btn, Mode.DES);
        onQuantityChangeBtn(food_select_increase_btn, Mode.INC);
    }

    private void onQuantityChangeBtn(LinearLayout btn, Mode mode) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == Mode.INC)
                    countQuantity++;
                else if (countQuantity >= 2)
                    countQuantity--;
                if (countQuantity == 1) {
                    food_select_decrease_btn.setBackgroundResource(R.drawable.shape_border_box_disable);
                } else {
                    food_select_decrease_btn.setBackgroundResource(R.drawable.shape_border_box);
                }
                updateQuantityUi();
            }
        });
    }

    private void backBtn(LinearLayout btn) {
        btn.setOnClickListener(v -> finish());
    }

    private void updateQuantityUi() {
        String price = String.format(Locale.getDefault(), "%,d" + Utils.CURRENCY, countQuantity * food.getPrice());
        String newBtnText = ADD_TO_CART_STR + price;
        food_select_quantity_text.setText(String.valueOf(countQuantity));
        food_select_add_to_cart_btn.setText(newBtnText);
    }

    private void addToCartBtn() {
        food_select_add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartGlobal.addFood(new CartFood(food, countQuantity));
                Toast.makeText(FoodSelectActivity.this, "" + cartGlobal.getFoods().size(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                setResult(2, intent);

                finish();
            }
        });
    }

}