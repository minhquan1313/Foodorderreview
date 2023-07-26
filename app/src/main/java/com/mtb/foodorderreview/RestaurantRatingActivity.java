package com.mtb.foodorderreview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.components.ExpandableHeightListView;
import com.mtb.foodorderreview.restaurentview.RestaurantUserRate;
import com.mtb.foodorderreview.restaurentview.RestaurantUserRateListViewAdapter;
import com.mtb.foodorderreview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRatingActivity extends AppCompatActivity {
    ExpandableHeightListView restaurant_rating_listview;

    LinearLayout restaurant_rating_back_btn;

    ImageView restaurant_rating_star_1_image,
            restaurant_rating_star_2_image,
            restaurant_rating_star_3_image,
            restaurant_rating_star_4_image,
            restaurant_rating_star_5_image;

    EditText restaurant_rating_note_input;

    Button restaurant_rating_submit_btn;
    int starCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_rating);

        initialization();
        starsClick();

        listview();

        Utils.UI.backBtn(this, restaurant_rating_back_btn);
    }

    private void initialization() {
        restaurant_rating_listview = findViewById(R.id.restaurant_rating_listview);
        restaurant_rating_back_btn = findViewById(R.id.restaurant_rating_back_btn);
        restaurant_rating_star_1_image = findViewById(R.id.restaurant_rating_star_1_image);
        restaurant_rating_star_2_image = findViewById(R.id.restaurant_rating_star_2_image);
        restaurant_rating_star_3_image = findViewById(R.id.restaurant_rating_star_3_image);
        restaurant_rating_star_4_image = findViewById(R.id.restaurant_rating_star_4_image);
        restaurant_rating_star_5_image = findViewById(R.id.restaurant_rating_star_5_image);
        restaurant_rating_note_input = findViewById(R.id.restaurant_rating_note_input);
        restaurant_rating_submit_btn = findViewById(R.id.restaurant_rating_submit_btn);
    }

    private void activeStar(ImageView star) {
        Utils.UI.setBackgroundTint(RestaurantRatingActivity.this, star, R.color.yellow);
        Utils.UI.setSrc(R.drawable.icon_star_fill, star);
    }

    private void deActiveStar(ImageView star) {
        Utils.UI.setBackgroundTint(RestaurantRatingActivity.this, star, R.color.grey_3);
        Utils.UI.setSrc(R.drawable.icon_star, star);
    }

    public void setStar(int count) {
        deActiveStar(restaurant_rating_star_1_image);
        deActiveStar(restaurant_rating_star_2_image);
        deActiveStar(restaurant_rating_star_3_image);
        deActiveStar(restaurant_rating_star_4_image);
        deActiveStar(restaurant_rating_star_5_image);


        switch (count) {
            case 5:
                activeStar(restaurant_rating_star_5_image);
            case 4:
                activeStar(restaurant_rating_star_4_image);
            case 3:
                activeStar(restaurant_rating_star_3_image);
            case 2:
                activeStar(restaurant_rating_star_2_image);
            case 1:
                activeStar(restaurant_rating_star_1_image);
        }
    }

    private void listview() {
        List<RestaurantUserRate> l = new ArrayList<>() {
            {
                add(new RestaurantUserRate("Anh tư", 5, "Quá ngon"));
                add(new RestaurantUserRate("Anh năm", 4, "Quá ngon"));
                add(new RestaurantUserRate("Anh Sáu", 1, "Quá nấu chưa chín, t quá thất vọng rồi"));
            }
        };

        RestaurantUserRateListViewAdapter adapter = new RestaurantUserRateListViewAdapter(RestaurantRatingActivity.this,
                l);
        restaurant_rating_listview.setAdapter(adapter);
    }

    private void starsClick() {
        starClick(restaurant_rating_star_1_image, 1);
        starClick(restaurant_rating_star_2_image, 2);
        starClick(restaurant_rating_star_3_image, 3);
        starClick(restaurant_rating_star_4_image, 4);
        starClick(restaurant_rating_star_5_image, 5);
    }

    private void starClick(ImageView star, int count) {
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starCount = count;
                // here here here here here here
                setStar(count);
            }
        });
    }

}