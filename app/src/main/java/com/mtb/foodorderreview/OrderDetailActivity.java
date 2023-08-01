package com.mtb.foodorderreview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.checkout.CartFoodListViewAdapter;
import com.mtb.foodorderreview.components.ExpandableHeightListView;
import com.mtb.foodorderreview.global.OrderGlobal;
import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.something.Order;
import com.mtb.foodorderreview.utils.Utils;

public class OrderDetailActivity extends AppCompatActivity {
    Context context;
    TextView order_detail_final_price,
            order_detail_id,
            order_detail_state,
            order_detail_restaurant_name;
    ExpandableHeightListView order_detail_listview;
    LinearLayout order_detail_back_btn,
            order_detail_restaurant_layout;

    Order order = OrderGlobal.getInstance().getOrder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        context = this;

        initial();

        bindData();
        Utils.UI.backBtn(context, order_detail_back_btn);
        adapterListView();
        restaurantClick();
    }


    private void initial() {
        order_detail_final_price = findViewById(R.id.order_detail_final_price);
        order_detail_id = findViewById(R.id.order_detail_id);
        order_detail_state = findViewById(R.id.order_detail_state);
        order_detail_listview = findViewById(R.id.order_detail_listview);
        order_detail_back_btn = findViewById(R.id.order_detail_back_btn);
        order_detail_restaurant_layout = findViewById(R.id.order_detail_restaurant_layout);
        order_detail_restaurant_name = findViewById(R.id.order_detail_restaurant_name);
    }

    private void bindData() {
        order_detail_final_price.setText(Utils.currency(order.getFinalPrice()));
        order_detail_id.setText(String.valueOf(order.getId()));
        order_detail_state.setText(order.getStateStr());
        order_detail_restaurant_name.setText(order.getRestaurant().getName());
    }

    private void adapterListView() {

        CartFoodListViewAdapter adapter = new CartFoodListViewAdapter(this, order.getCartFood());
        order_detail_listview.setAdapter(adapter);
        order_detail_listview.setExpanded(true);
    }

    private void restaurantClick() {
        order_detail_restaurant_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantGlobal.getInstance().setRestaurant(order.getRestaurant());
                Intent intent = new Intent(context, RestaurantActivity.class);
                startActivity(intent);
            }
        });
    }
}