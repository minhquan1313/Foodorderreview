package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainEmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empty);

        Intent activityIntent;

        // go straight to main if a token is stored
        if (false) {
            activityIntent = new Intent(this, MainActivity.class);
        } else {
            activityIntent = new Intent(this, AuthActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }
}