package com.mtb.foodorderreview;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    CardView profile_avatar_cardview;
    ImageView profile_avatar_image;
    TextView profile_id_text,
            profile_username_text;
    EditText profile_name_inp,
            profile_tel_inp,
            profile_email_inp,
            profile_address_inp;
    Button profile_save_btn;
    Context context;
    String name,
            tel,
            email,
            address;
    UserGlobal userGlobal = UserGlobal.getInstance();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getContext();

        initialization(view);
        bindData();
        makeChangeListener(profile_name_inp, name);
        makeChangeListener(profile_tel_inp, tel);
        makeChangeListener(profile_email_inp, email);
        makeChangeListener(profile_address_inp, address);
        btnSubmit();

        return view;
    }

    private void initialization(View view) {
        profile_avatar_cardview = view.findViewById(R.id.profile_avatar_cardview);
        profile_avatar_image = view.findViewById(R.id.profile_avatar_image);
        profile_id_text = view.findViewById(R.id.profile_id_text);
        profile_username_text = view.findViewById(R.id.profile_username_text);
        profile_name_inp = view.findViewById(R.id.profile_name_inp);
        profile_tel_inp = view.findViewById(R.id.profile_tel_inp);
        profile_email_inp = view.findViewById(R.id.profile_email_inp);
        profile_address_inp = view.findViewById(R.id.profile_address_inp);
        profile_save_btn = view.findViewById(R.id.profile_save_btn);
    }

    private void btnSubmit() {
        profile_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call api here
                updateVariables();
                if ("callApiSuccess" != null) {
                    userGlobal.setName(name);
                    userGlobal.setAddress(address);
                    userGlobal.setEmail(email);
                    userGlobal.setTel(tel);

                    bindData();
                }
            }
        });
    }

    private void makeChangeListener(EditText editText, String init) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (str.trim().equals(init)) {
                    disableBtn();
                    return;
                }

                enableBtn();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void updateVariables() {
        name = profile_name_inp.getText().toString();
        tel = profile_tel_inp.getText().toString();
        email = profile_email_inp.getText().toString();
        address = profile_address_inp.getText().toString();
    }

    private void bindData() {
        name = userGlobal.getName().trim();
        tel = userGlobal.getTel().trim();
        email = userGlobal.getEmail().trim();
        address = userGlobal.getAddress().trim();

        profile_avatar_image.setImageResource(userGlobal.getAvatar());
        profile_id_text.setText(String.valueOf(userGlobal.getId()));
        profile_username_text.setText(userGlobal.getUserName().trim());

        profile_name_inp.clearFocus();
        profile_tel_inp.clearFocus();
        profile_email_inp.clearFocus();
        profile_address_inp.clearFocus();

        profile_name_inp.setText(name);
        profile_tel_inp.setText(tel);
        profile_email_inp.setText(email);
        profile_address_inp.setText(address);

        disableBtn();
    }

    private void disableBtn() {
        profile_save_btn.setEnabled(false);
        Utils.UI.setBackgroundTint(context, profile_save_btn, R.color.grey_3);
    }

    private void enableBtn() {
        profile_save_btn.setEnabled(true);
        Utils.UI.setBackgroundTint(context, profile_save_btn);
    }

}