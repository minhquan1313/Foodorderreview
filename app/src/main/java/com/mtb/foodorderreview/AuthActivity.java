package com.mtb.foodorderreview;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;

import com.mtb.foodorderreview.api.UserService;
import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.model.LoaiFood;
import com.mtb.foodorderreview.model.Login;
import com.mtb.foodorderreview.model.Message;
import com.mtb.foodorderreview.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    EditText sign_up_tel_input,
            sign_up_username_input,
            sign_up_password_input,
            sign_up_name_input,
            sign_up_email_input,
            sign_up_address_input;
    TextView fragment_sign_up_response_text;
    EditText
            sign_in_username_input,
            sign_in_password_input;
    TextView fragment_sign_in_response_text;
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
        sign_in_username_input = findViewById(R.id.sign_in_username_input);
        sign_in_password_input = findViewById(R.id.sign_in_password_input);
        fragment_sign_in_response_text = findViewById(R.id.fragment_sign_in_response_text);
        Login login = new Login(sign_in_username_input.getText().toString(),sign_in_password_input.getText().toString());

        UserService.apiService.login(login).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Message message = response.body();
                if(message.getStatus() == true)
                    onSuccessHandle();
                else
                    onFailHandle("sai tài khoản or mật khẩu");
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
        // Call your API here
        disableButton(auth_sign_up_btn1);
        disableButton(auth_sign_in_btn1);

//        if (authUsername.trim().length() != 0 && authPassword.trim().length() != 0) {
//            requestId = "1";
//            requestToken = "abcxyz";
//            requestIsAdmin = false;
//            authName = "Ngài Bình";
//
//            onSuccessHandle();
//        } else {
//            onFailHandle("Nhập đại thông tin đăng nhập đi");
//        }
    }

    private void signUp() {
        sign_up_tel_input = findViewById(R.id.sign_up_tel_input);
        sign_up_username_input = findViewById(R.id.sign_up_username_input);
        sign_up_password_input = findViewById(R.id.sign_up_password_input);
        sign_up_name_input = findViewById(R.id.sign_up_name_input);
        sign_up_email_input = findViewById(R.id.sign_up_email_input);
        sign_up_address_input = findViewById(R.id.sign_up_address_input);
        fragment_sign_up_response_text = findViewById(R.id.fragment_sign_up_response_text);
        User user = new User(null,sign_up_name_input.getText().toString(),
                sign_up_username_input.getText().toString()
                ,sign_up_password_input.getText().toString(),
                sign_up_address_input.getText().toString(),
                sign_up_tel_input.getText().toString(),
                null,
                sign_up_email_input.getText().toString(),
                null
        );
        UserService.apiService.register(user).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Message message = response.body();
                Toast.makeText(AuthActivity.this,message.getMessage().toString(),Toast.LENGTH_LONG).show();
                onSuccessHandle();

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
            }
        });
        // Call your API here
        disableButton(auth_sign_up_btn1);
        disableButton(auth_sign_in_btn1);


        /**
         * if(success) onSuccessHandle()
         * else onFailHandle("Ten dang nhap bi trung")
         */



    }

    private void disableButton(Button btn) {
        btn.setEnabled(false);
    }

    private void enableButton(Button btn) {
        btn.setEnabled(true);
    }

    private void onSuccessHandle() {
//        UserGlobal.getInstance().setData(
//                Integer.parseInt(requestId),
//                authUsername,
//                authName,
//                authTel,
//                R.drawable.img_sample_user_avatar,
//                authEmail,
//                authAddress,
//                requestToken,
//                requestIsAdmin);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void onFailHandle(String msg) {
        authResponse = msg;
        enableButton(auth_sign_in_btn1);
        enableButton(auth_sign_up_btn1);
        changeUI(state);
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