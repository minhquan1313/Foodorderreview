package com.mtb.foodorderreview;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialization();

        bottomNavigation();
    }

    private void initialization() {
        navigationView = findViewById(R.id.bottom_navigation1);

        fragmentManager = getSupportFragmentManager();

    }

    private void bottomNavigation() {
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
                    fragmentManager
                            .beginTransaction()
                            .replace(mainFrameId, HomePageFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("home") // Name can be null
                            .commit();
                }

                if (id == R.id.bottom_navigation_order1) {
                }

                if (id == R.id.bottom_navigation_profile1) {
                }

                return true;
            }
        });
    }

}