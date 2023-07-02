package com.mtb.foodorderreview;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.mtb.foodorderreview.homeview.HomeFoodType;
import com.mtb.foodorderreview.homeview.HomeFoodTypeGridAdapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        HomeFoodTypeUI();
    }

    private void HomeFoodTypeUI() {

        HomeFoodType[] l = {
                new HomeFoodType("Rice", R.drawable.icon_food_type_rice),
                new HomeFoodType("Rice2", R.drawable.icon_food_type_rice),
                new HomeFoodType("Rice3", R.drawable.icon_food_type_rice),
                new HomeFoodType("Rice4", R.drawable.icon_food_type_rice),
                new HomeFoodType("Rice5", R.drawable.icon_food_type_rice)
        };


        //Toast.makeText(this, "size = " + list.size(), Toast.LENGTH_SHORT).show();
        HomeFoodTypeGridAdapter adapter = new HomeFoodTypeGridAdapter(MainActivity.this, Arrays.asList(l));
        Toast.makeText(this, "adapter = " + adapter.getCount(), Toast.LENGTH_SHORT).show();

        GridView gridView = findViewById(R.id.home_food_type_grid_1);
        gridView.setAdapter(adapter);

        //Toast.makeText(this, "Hihi", Toast.LENGTH_SHORT).show();

        // gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        // @Override
        // public void onItemClick(AdapterView<?> parent, View view, int position, long
        // id) {
        // Object o = gridView.getItemAtPosition(position);
        // HomeFoodType foodType = (HomeFoodType) o;
        // Toast.makeText(MainActivity.this, "Selected :" + " " + foodType.getName(),
        // Toast.LENGTH_LONG).show();
        // }
        // });
    }

    private void Init() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // int mainFrameId = 0;
        int mainFrameId = R.id.main_frame_layout1;

        fragmentManager
                .beginTransaction()
                .replace(mainFrameId, HomePageFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("home") // Name can be null
                .commit();
    }
}