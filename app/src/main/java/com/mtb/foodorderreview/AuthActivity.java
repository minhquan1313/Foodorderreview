package com.mtb.foodorderreview;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;

import com.mtb.foodorderreview.global.UserGlobal;

public class AuthActivity extends AppCompatActivity {

    private String authUsername = "example",
            authPassword = "example",
            authTel = "",
            authName = "",
            authAddress = "",
            authResponse = "",
            authEmail = "",
            requestId = "",
            requestToken = "";
    private Boolean
            requestIsAdmin = false;

    enum State {
        SIGN_IN, SIGN_UP
    }


    private Button auth_sign_in_btn1, auth_sign_up_btn1;
    private State state = State.SIGN_UP;

    private TextView auth_title1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        init();
    }

    private void init() {
        auth_sign_in_btn1 = findViewById(R.id.auth_sign_in_btn1);
        auth_sign_up_btn1 = findViewById(R.id.auth_sign_up_btn1);
        auth_title1 = findViewById(R.id.auth_title1);

        changeUI(state);

        auth_sign_in_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == State.SIGN_IN)
                    signIn();
                else {
                    state = state == State.SIGN_IN ? State.SIGN_UP : State.SIGN_IN;
                    authResponse = "";
                    changeUI(state);
                }
            }
        });
        auth_sign_up_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == State.SIGN_UP)
                    signUp();
                else {
                    state = state == State.SIGN_IN ? State.SIGN_UP : State.SIGN_IN;
                    authResponse = "";
                    changeUI(state);
                }
            }
        });
    }

    private void changeUI(State state) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int frameId = R.id.auth_frame_layout1;

        auth_title1.setText(state == State.SIGN_IN ? "Đăng nhập" : "Đăng ký");

        fragmentManager
                .beginTransaction()
                .replace(frameId, state == State.SIGN_IN ? SignInFragment.class : SignUpFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("home") // Name can be null
                .commit();

        Typeface typeface = ResourcesCompat.getFont(this, R.font.svn_poppins_semibold);

        if (state == State.SIGN_IN) {
            auth_sign_in_btn1.setBackgroundResource(R.drawable.shape_home_button_order_now);
            auth_sign_in_btn1.setTypeface(typeface);

            auth_sign_up_btn1.setBackgroundResource(R.drawable.shape_button_alt);
            auth_sign_up_btn1.setTypeface(null);

        } else {
            auth_sign_in_btn1.setBackgroundResource(R.drawable.shape_button_alt);
            auth_sign_in_btn1.setTypeface(null);

            auth_sign_up_btn1.setBackgroundResource(R.drawable.shape_home_button_order_now);
            auth_sign_up_btn1.setTypeface(typeface);
        }


    }

    private void signIn() {
        // Call your API here
        disableButton(auth_sign_up_btn1);
        disableButton(auth_sign_in_btn1);
        String s = authUsername + " " +
                authPassword + " ";

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

        if (authUsername.trim().length() != 0 && authPassword.trim().length() != 0) {
            requestId = "1";
            requestToken = "abcxyz";
            requestIsAdmin = false;
            authName = "Ngài Bình";

            onSuccessHandle();
        } else {
            onFailHandle("Nhập đại thông tin đăng nhập đi");
        }
    }

    private void signUp() {
        // Call your API here
        disableButton(auth_sign_up_btn1);
        disableButton(auth_sign_in_btn1);

        String s = authUsername + " " +
                authPassword + " " +
                authTel + " " +
                authName + " " +
                authAddress + " " +
                authEmail;

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

        /**
         * if(success) onSuccessHandle()
         * else onFailHandle("Ten dang nhap bi trung")
         */


        onFailHandle("Ai cho mi đăng ký:))");
    }

    private void disableButton(Button btn) {
        btn.setEnabled(false);
    }

    private void enableButton(Button btn) {
        btn.setEnabled(true);
    }

    private void onSuccessHandle() {
        MainEmptyActivity.fakeUserFromAPI(authUsername, authPassword);

        UserGlobal.writeUserToStorage(this, authUsername, authPassword);

        //finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void onFailHandle(String msg) {
        authResponse = msg;
        enableButton(auth_sign_in_btn1);
        enableButton(auth_sign_up_btn1);
        changeUI(state);
    }

    @Override
    public void onBackPressed() {

    }

    public String getString(String bundleName) {
        if ("authUsername".equals(bundleName))
            return authUsername;
        if ("authPassword".equals(bundleName))
            return authPassword;
        if ("authTel".equals(bundleName))
            return authTel;
        if ("authName".equals(bundleName))
            return authName;
        if ("authAddress".equals(bundleName))
            return authAddress;
        if ("authResponse".equals(bundleName))
            return authResponse;
        if ("authEmail".equals(bundleName))
            return authEmail;

        return "";
    }

    public void setString(String bundleName, String value) {
        if ("authUsername".equals(bundleName)) {
            authUsername = value;
            return;
        }
        if ("authPassword".equals(bundleName)) {
            authPassword = value;
            return;
        }
        if ("authTel".equals(bundleName)) {
            authTel = value;
            return;
        }
        if ("authName".equals(bundleName)) {
            authName = value;
            return;
        }
        if ("authAddress".equals(bundleName)) {
            authAddress = value;
            return;
        }
        if ("authResponse".equals(bundleName)) {
            authResponse = value;
            return;
        }
        if ("authEmail".equals(bundleName)) {
            authEmail = value;
            return;
        }
    }
}