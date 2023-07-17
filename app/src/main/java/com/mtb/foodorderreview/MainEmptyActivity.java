package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.global.User;

public class MainEmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empty);

        Intent activityIntent;

//        initFakeUser();

        if (User.getInstance().getToken() != null) {
            activityIntent = new Intent(this, MainActivity.class);
        } else {
            activityIntent = new Intent(this, AuthActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }

    private void initFakeUser() {
        User.getInstance().setData(
                1,
                "Anh_Ba",
                "Anh Ba",
                "0123456789",
                R.drawable.img_user_avatar,
                "abcxyz123@gmail.com",
                "Thành Phố HCM",
                "lakjwdliawjdljia",
                false);
    }
}