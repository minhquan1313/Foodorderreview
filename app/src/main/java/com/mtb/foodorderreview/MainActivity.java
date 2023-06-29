package com.mtb.foodorderreview;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout layout = findViewById(R.id.LinearLayout1);
        layout.setOnClickListener(v -> {
            Toast.makeText(this, "Hhihi", Toast.LENGTH_SHORT).show();
        });
    }
}