package com.mtb.foodorderreview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        BundlePart();
    }

    private void BundlePart() {
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        String name = bundle.getString("name");

        TextView test1 = findViewById(R.id.test1);
        String t = id + " - " + name;
        
        test1.setText(t);
    }
}