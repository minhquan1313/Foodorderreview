package com.mtb.foodorderreview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.components.ExpandableHeightGridView;
import com.mtb.foodorderreview.global.CartGlobal;
import com.mtb.foodorderreview.global.OrderGlobal;
import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.global.RestaurantListGlobal;
import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.homeview.FoodType;
import com.mtb.foodorderreview.homeview.FoodTypeGridAdapter;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.homeview.RestaurantRecyclerAdapter;
import com.mtb.foodorderreview.service.FoodCategoryType;
import com.mtb.foodorderreview.utils.IChangeListener;
import com.mtb.foodorderreview.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment {
    TextView home_page_user_name,
            home_shipping_quantity_text;
    ImageView home_user_avatar1,
            home_cart_btn_image;
    RelativeLayout home_shipping_btn;
    Button home_page_view_all_restaurant_btn,
            home_coupon_order_now_btn;
    LinearLayout home_cart_btn;
    EditText home_search_inp;
    Context context;
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
        context = getContext();
        initialization(view);
        homeFoodTypeUI(context, view);
        homeFoodShopUI(context, view);
        viewAllResBtn(context, view);
        couponOrderNowBtn(context, view);
        cartBtn(context);
        updateCartBtnUi(context);
        shippingBtn(context);
        updateShippingBtnUi();

        OrderGlobal.getInstance().addListener(new IChangeListener<OrderGlobal>() {
            @Override
            public int getId() {
                return 1;
            }

            @Override
            public void dataChange(OrderGlobal obj) {
                updateShippingBtnUi();
            }
        });
        CartGlobal.getInstance().addListener(new IChangeListener<CartGlobal>() {
            @Override
            public int getId() {
                return 2;
            }

            @Override
            public void dataChange(CartGlobal obj) {
                updateCartBtnUi(context);
            }
        });
        // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        return view;
    }

    private void initialization(View v) {
        home_page_user_name = v.findViewById(R.id.home_page_user_name);
        home_user_avatar1 = v.findViewById(R.id.home_user_avatar1);
        home_cart_btn_image = v.findViewById(R.id.home_cart_btn_image);
        home_page_view_all_restaurant_btn = v.findViewById(R.id.home_page_view_all_restaurant_btn);
        home_coupon_order_now_btn = v.findViewById(R.id.home_coupon_order_now_btn);
        home_cart_btn = v.findViewById(R.id.home_cart_btn);
        home_search_inp = v.findViewById(R.id.home_search_inp);
        home_shipping_btn = v.findViewById(R.id.home_shipping_btn);
        home_shipping_quantity_text = v.findViewById(R.id.home_shipping_quantity_text);

        home_page_user_name.setText(UserGlobal.getInstance().getName());

        // String url =
        // "https://cdn.britannica.com/16/234216-050-C66F8665/beagle-hound-dog.jpg";
        // Utils.UI.setSrc(url, home_user_avatar1);
    }

    private List<Restaurant> getAllRestaurants() {
        Restaurant[] l = {
                new Restaurant(1, "Cháo lòng bà Bảy", Utils.URL_SAMPLE_IMAGE,
                        "262 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(2, "b", Utils.URL_SAMPLE_IMAGE,
                        "263 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(3, "Cơm tấm anh da đen", Utils.URL_SAMPLE_IMAGE,
                        "264 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(4, "d", Utils.URL_SAMPLE_IMAGE,
                        "265 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(5, "e", Utils.URL_SAMPLE_IMAGE,
                        "266 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),

                new Restaurant(6, "f", Utils.URL_SAMPLE_IMAGE,
                        "267 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),
                new Restaurant(7, "Tau hu", Utils.URL_SAMPLE_IMAGE,
                        "267 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),
                new Restaurant(8, "Com binh dan", Utils.URL_SAMPLE_IMAGE,
                        "267 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),
                new Restaurant(9, "Mi cay", Utils.URL_SAMPLE_IMAGE,
                        "267 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),
                new Restaurant(10, "Oc de nhat", Utils.URL_SAMPLE_IMAGE,
                        "267 Lạc Long Quân, Phường 5, Quận 11, Thành phố Hồ Chí Minh, Việt Nam"),
        };

        return Arrays.asList(l);
    }

    private void viewAllResBtn(Context context, View view) {
        home_page_view_all_restaurant_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantListGlobal.getInstance().setList(getAllRestaurants());

                Intent intent = new Intent(context, RestaurantListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void couponOrderNowBtn(Context context, View view) {
        home_coupon_order_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantListGlobal.getInstance().setList(getAllRestaurants());

                Intent intent = new Intent(context, RestaurantListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void homeFoodShopUI(Context context, View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);

        List<Restaurant> l = getAllRestaurants().stream().limit(5).collect(Collectors.toList());
        RestaurantRecyclerAdapter adapter = new RestaurantRecyclerAdapter(context, l);

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

    private void homeFoodTypeUI(Context context, View view) {
        FoodType[] l = {
                new FoodType(FoodCategoryType.RICE, "Cơm", R.drawable.img_food_type_rice),
                new FoodType(FoodCategoryType.COFFEE, "Cà phê", R.drawable.img_food_type_coffee),
                new FoodType(FoodCategoryType.NODDLE, "Mì", R.drawable.img_food_type_noddle),
                new FoodType(FoodCategoryType.FAST_FOOD, "Fast food", R.drawable.img_food_type_fastfood),
                new FoodType(FoodCategoryType.MILK_TEA, "Trà sữa", R.drawable.img_food_type_milktea),
                new FoodType(FoodCategoryType.SNACK, "Snack", R.drawable.img_food_type_snack),
                new FoodType(FoodCategoryType.SPECIALTY, "Đặc trưng", R.drawable.img_food_type_specialty),
                new FoodType(FoodCategoryType.HEALTHY, "Healthy", R.drawable.img_food_type_healthy)
        };

        FoodTypeGridAdapter adapter = new FoodTypeGridAdapter(context, Arrays.asList(l));

        ExpandableHeightGridView gridView = view.findViewById(R.id.home_food_type_grid_1);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            FoodType foodType = (FoodType) gridView.getItemAtPosition(position);

            int foodTypeId = foodType.getId();
            List<Restaurant> restaurants;

            switch (foodTypeId) {
                case FoodCategoryType.RICE:
                    restaurants = getAllRestaurants();
                    break;
                case FoodCategoryType.COFFEE:
                    restaurants = getAllRestaurants();

                    break;
                case FoodCategoryType.NODDLE:
                    restaurants = getAllRestaurants();

                    break;
                case FoodCategoryType.FAST_FOOD:
                    restaurants = getAllRestaurants();

                    break;
                case FoodCategoryType.MILK_TEA:
                    restaurants = getAllRestaurants();

                    break;
                case FoodCategoryType.SNACK:
                    restaurants = getAllRestaurants();

                    break;
                case FoodCategoryType.SPECIALTY:
                    restaurants = getAllRestaurants();

                    break;
                case FoodCategoryType.HEALTHY:
                    restaurants = getAllRestaurants();
                    break;
                default:
                    restaurants = getAllRestaurants();
            }

            RestaurantListGlobal.getInstance().setList(restaurants);

            Intent intent = new Intent(context, RestaurantListActivity.class);
            intent.putExtra("TITLE", foodType.getName());
            startActivity(intent);
        });
    }

    private void cartBtn(Context context) {
        home_cart_btn.setOnClickListener(v -> {
            Intent intent = new Intent(context, CartCheckoutActivity.class);
            getActivity().startActivityIfNeeded(intent, 3);
        });
    }

    private void shippingBtn(Context context) {

        home_shipping_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OrderGlobal.getInstance().getOrder() == null)
                    return;

                Intent intent = new Intent(context, DeliveryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void updateShippingBtnUi() {
        home_shipping_btn.setVisibility(View.INVISIBLE);

        OrderGlobal orderGlobal = OrderGlobal.getInstance();
        if (orderGlobal.getOrder() == null)
            return;

        home_shipping_btn.setVisibility(View.VISIBLE);
        home_shipping_quantity_text.setText("1");
    }

    private void updateCartBtnUi(Context context) {
        CartGlobal cartGlobal = CartGlobal.getInstance();
        if (cartGlobal.getFoodList().size() == 0) {
            home_cart_btn.setBackgroundResource(R.drawable.shape_border_box);
            Utils.UI.setBackgroundTint(context, home_cart_btn_image, R.color.grey_5);
            return;
        }

        home_cart_btn.setBackgroundResource(R.drawable.shape_border_box_primary);
        Utils.UI.setBackgroundTint(context, home_cart_btn_image, R.color.primary);

    }
}