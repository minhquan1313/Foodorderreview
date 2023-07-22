package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.global.RestaurantListGlobal;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.homeview.RestaurantListViewAdapter;
import com.mtb.foodorderreview.utils.Utils;

public class RestaurantListActivity extends AppCompatActivity {
    ListView restaurant_list_listview;
    TextView restaurant_list_header_text;
    LinearLayout restaurant_list_back_btn;

    RestaurantListGlobal restaurantListGlobal = RestaurantListGlobal.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        initialization();

        Utils.UI.backBtn(this, restaurant_list_back_btn);
        setRestaurantListV();
    }

    private void initialization() {
        restaurant_list_listview = findViewById(R.id.restaurant_list_listview);
        restaurant_list_header_text = findViewById(R.id.restaurant_list_header_text);
        restaurant_list_back_btn = findViewById(R.id.restaurant_list_back_btn);

        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");

        if (title != null) {
            String originalTitle = restaurant_list_header_text.getText().toString();
            String newTitle = originalTitle + " - " + title;
            restaurant_list_header_text.setText(newTitle);
        }
    }

    private void setRestaurantListV() {

        RestaurantListViewAdapter adapter = new RestaurantListViewAdapter(this, restaurantListGlobal.getList());

        restaurant_list_listview.setAdapter(adapter);
        restaurant_list_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant f = (Restaurant) restaurant_list_listview.getItemAtPosition(position);
                RestaurantGlobal.getInstance().setRestaurant(f);

                Intent intent = new Intent(RestaurantListActivity.this, RestaurantActivity.class);
                startActivity(intent);
            }
        });
    }


}