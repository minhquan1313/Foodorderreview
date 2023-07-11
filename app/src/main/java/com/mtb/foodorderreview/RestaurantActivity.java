package com.mtb.foodorderreview;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        BundlePart();
        Miscellaneous();
    }

    private void Miscellaneous() {
        LinearLayout linear_btn_restaurant_back1 = findViewById(R.id.linear_btn_restaurant_back1);

        linear_btn_restaurant_back1.setOnClickListener(v -> finish());
    }

    private void BundlePart() {
        Bundle bundle = getIntent().getExtras();

        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        int image = bundle.getInt("image");


    }
}