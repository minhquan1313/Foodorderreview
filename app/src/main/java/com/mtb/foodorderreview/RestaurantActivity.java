package com.mtb.foodorderreview;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView test1 = findViewById(R.id.test1);
        String t = id + " - " + name;

        test1.setOnClickListener(v -> Toast.makeText(RestaurantActivity.this, "Hihi back ne", Toast.LENGTH_SHORT).show());
        test1.setText(t);
    }
}