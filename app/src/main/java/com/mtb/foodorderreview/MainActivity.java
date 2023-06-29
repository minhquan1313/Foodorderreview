package com.mtb.foodorderreview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFoodTypeUI();
    }

    private void HomeFoodTypeUI() {
        HomeFoodType rice = new HomeFoodType("Rice", R.drawable.icon_food_type_rice);
        HomeFoodType rice2 = new HomeFoodType("Rice2", R.drawable.icon_food_type_rice);
        HomeFoodType rice3 = new HomeFoodType("Rice3", R.drawable.icon_food_type_rice);
        HomeFoodType rice4 = new HomeFoodType("Rice4", R.drawable.icon_food_type_rice);
        HomeFoodType rice5 = new HomeFoodType("Rice5", R.drawable.icon_food_type_rice);

        HomeFoodType[] l = {rice, rice2, rice3, rice4, rice5};

        HomeFoodTypeGridAdapter adapter = new HomeFoodTypeGridAdapter(this, Arrays.asList(l));

        GridView gridView = findViewById(R.id.home_food_type_grid_1);
        gridView.setAdapter(adapter);

        Toast.makeText(this, "Hihi", Toast.LENGTH_SHORT).show();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                HomeFoodType foodType = (HomeFoodType) o;
                Toast.makeText(MainActivity.this, "Selected :" + " " + foodType.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}