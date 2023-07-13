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

public class AuthActivity extends AppCompatActivity {
    enum State {
        SIGN_IN, SIGN_UP
    }

    Button auth_sign_in_btn1, auth_sign_up_btn1;
    State state = State.SIGN_IN;
    TextView auth_title1;


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


        changeStateUI(state);

        auth_sign_in_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == State.SIGN_IN) signIn();
                else {
                    state = state == State.SIGN_IN ? State.SIGN_UP : State.SIGN_IN;
                    changeStateUI(state);
                }
            }
        });
        auth_sign_up_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == State.SIGN_UP) signUp();
                else {
                    state = state == State.SIGN_IN ? State.SIGN_UP : State.SIGN_IN;
                    changeStateUI(state);
                }
            }
        });
    }

    private void changeStateUI(State state) {
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
        Toast.makeText(this, "Hihi signin", Toast.LENGTH_SHORT).show();

    }

    private void signUp() {
        Toast.makeText(this, "Hihi signup", Toast.LENGTH_SHORT).show();
    }

    private void onSuccessHandle() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}