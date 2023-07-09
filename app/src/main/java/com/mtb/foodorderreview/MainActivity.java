package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
    }

    private void Init() {
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation1);
        Intent restaurantIntent = new Intent(this, HomePageFragment.class);

        FragmentManager fragmentManager = getSupportFragmentManager();
        int mainFrameId = R.id.main_frame_layout1;

        fragmentManager
                .beginTransaction()
                .replace(mainFrameId, HomePageFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("home") // Name can be null
                .commit();

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked())
                    return false;
                int id = item.getItemId();

                if (id == R.id.bottom_navigation_home1) {
                    Toast.makeText(MainActivity.this, "Home ne", Toast.LENGTH_SHORT).show();
                    fragmentManager
                            .beginTransaction()
                            .replace(mainFrameId, HomePageFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("home") // Name can be null
                            .commit();
                }

                if (id == R.id.bottom_navigation_order1) {
                    Toast.makeText(MainActivity.this, "Order ne", Toast.LENGTH_SHORT).show();
                }

                if (id == R.id.bottom_navigation_profile1) {
                    Toast.makeText(MainActivity.this, "Profile ne", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }
}