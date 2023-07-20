package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.global.UserGlobal;

public class MainEmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empty);

        Intent activityIntent;

//        initFakeUser();

        if (UserGlobal.getInstance().getToken() != null) {
            activityIntent = new Intent(this, MainActivity.class);
        } else {
            activityIntent = new Intent(this, AuthActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }

    private void initFakeUser() {
        UserGlobal.getInstance().setData(
                1,
                "Anh_Ba",
                "Anh Ba",
                "0123456789",
                R.drawable.img_sample_user_avatar,
                "abcxyz123@gmail.com",
                "Thành Phố HCM",
                "lakjwdliawjdljia",
                false);
    }
}