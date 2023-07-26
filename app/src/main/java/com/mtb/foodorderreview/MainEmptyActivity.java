package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.utils.Utils;

public class MainEmptyActivity extends AppCompatActivity {
    protected static final String USER_DATA_USERNAME_FILE = "user_info_username";
    protected static final String USER_DATA_PASSWORD_FILE = "user_info_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empty);

        Intent activityIntent;

        String username = Utils.readFile(this, USER_DATA_USERNAME_FILE);
        String pass = Utils.readFile(this, USER_DATA_PASSWORD_FILE);

//        fakeUserFromAPI(username, pass);
        // Call api login here

        if (UserGlobal.getInstance().getUserName() != null) {
            activityIntent = new Intent(this, MainActivity.class);
        } else {
            activityIntent = new Intent(this, AuthActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }

    private void fakeUserFromAPI(String username, String password) {
        if (username.equals("example") && password.equals("example")) {
            UserGlobal.getInstance().setData(
                    1,
                    username,
                    "Anh Ba",
                    "0123456789",
                    R.drawable.img_sample_user_avatar,
                    "abcxyz123@gmail.com",
                    "Thành Phố HCM",
                    "lakjwdliawjdljia",
                    false);
        }
    }
}