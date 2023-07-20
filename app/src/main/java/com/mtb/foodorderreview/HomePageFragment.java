package com.mtb.foodorderreview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.components.ExpandableHeightGridView;
import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.homeview.FoodType;
import com.mtb.foodorderreview.homeview.FoodTypeGridAdapter;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.homeview.RestaurantRecyclerAdapter;
import com.mtb.foodorderreview.service.FoodCategoryType;
import com.mtb.foodorderreview.utils.Utils;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    TextView home_page_user_name;
    ImageView home_user_avatar1;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        initialization(view);
        HomeFoodTypeUI(getContext(), view);
        HomeFoodShopUI(getContext(), view);
        // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        return view;
    }

    private void initialization(View v) {
        home_page_user_name = v.findViewById(R.id.home_page_user_name);
        home_user_avatar1 = v.findViewById(R.id.home_user_avatar1);

        home_page_user_name.setText(UserGlobal.getInstance().getName());

        String url = "https://cdn.britannica.com/16/234216-050-C66F8665/beagle-hound-dog.jpg";
        Utils.UI.setSrc(url, home_user_avatar1);
    }

    private void HomeFoodShopUI(Context context, View view) {
        Restaurant[] l = {
                new Restaurant(1, "Cháo lòng bà Bảy", R.drawable.img_sample_food,
                        "262 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(2, "b", R.drawable.img_sample_food,
                        "263 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(3, "Cơm tấm anh da đen", R.drawable.img_sample_food_2,
                        "264 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(4, "d", R.drawable.img_sample_food,
                        "265 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(5, "e", R.drawable.img_sample_food,
                        "266 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(6, "f", R.drawable.img_sample_food,
                        "267 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam")
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                false);
        RestaurantRecyclerAdapter adapter = new RestaurantRecyclerAdapter(context, Arrays.asList(l));

        adapter.setClickListener((view1, position, isLongClick, homeFood) -> {
            if (!isLongClick) {
                RestaurantGlobal.getInstance().setRestaurant(homeFood);

                Intent intent = new Intent(context, RestaurantActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.home_food_shop_recycler_1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void HomeFoodTypeUI(Context context, View view) {
        FoodType[] l = {
                new FoodType(FoodCategoryType.RICE, "Rice", R.drawable.icon_food_type_rice),
                new FoodType(FoodCategoryType.RICE, "Rice2", R.drawable.icon_food_type_rice),
                new FoodType(FoodCategoryType.RICE, "Rice3", R.drawable.icon_food_type_rice),
                new FoodType(FoodCategoryType.RICE, "Rice4", R.drawable.icon_food_type_rice),
                new FoodType(FoodCategoryType.RICE, "Rice5", R.drawable.icon_food_type_rice)
        };

        FoodTypeGridAdapter adapter = new FoodTypeGridAdapter(context, Arrays.asList(l));

        ExpandableHeightGridView gridView = view.findViewById(R.id.home_food_type_grid_1);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            FoodType foodType = (FoodType) gridView.getItemAtPosition(position);
        });
    }
}