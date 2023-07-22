package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.checkout.CartFoodListViewAdapter;
import com.mtb.foodorderreview.components.ExpandableHeightListView;
import com.mtb.foodorderreview.global.CartGlobal;
import com.mtb.foodorderreview.global.OrderGlobal;
import com.mtb.foodorderreview.model.DonHang;
import com.mtb.foodorderreview.something.Order;
import com.mtb.foodorderreview.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CartCheckoutActivity extends AppCompatActivity {
    ExpandableHeightListView cart_checkout_listview;
    Button cart_checkout_submit_btn;
    TextView cart_checkout_subtotal_text,
            cart_checkout_shipping_text,
            cart_checkout_discount_text,
            cart_checkout_total_text;
    LinearLayout cart_checkout_back_btn;
    CartGlobal cartGlobal;

    final int SHIPPING_FEE = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_checkout);

        initialization();

        Utils.UI.backBtn(this, cart_checkout_back_btn);

        listView();

        updateUIPrice();

        orderSubmit();
    }

    private void initialization() {
        cart_checkout_listview = findViewById(R.id.cart_checkout_listview);
        cart_checkout_submit_btn = findViewById(R.id.cart_checkout_submit_btn);
        cart_checkout_subtotal_text = findViewById(R.id.cart_checkout_subtotal_text);
        cart_checkout_shipping_text = findViewById(R.id.cart_checkout_shipping_text);
        cart_checkout_discount_text = findViewById(R.id.cart_checkout_discount_text);
        cart_checkout_total_text = findViewById(R.id.cart_checkout_total_text);
        cart_checkout_back_btn = findViewById(R.id.cart_checkout_back_btn);

        cartGlobal = CartGlobal.getInstance();
    }

    private void orderSubmit() {
        cart_checkout_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order order = new Order(cartGlobal.getRestaurant(), cartGlobal.getFoodList());
                DonHang donHang = new DonHang(null,3, LocalDateTime.now(),"abbb",1);
                OrderGlobal.getInstance().setOrder(order);

                cartGlobal.reset();

                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });
    }

    private void listView() {
        CartFoodListViewAdapter adapter = new CartFoodListViewAdapter(this, CartGlobal.getInstance().getFoodList());

        cart_checkout_listview.setAdapter(adapter);
        cart_checkout_listview.setExpanded(true);
    }

    private void updateUIPrice() {
        int subtotal = cartGlobal.calSubtotal();
        int shippingFee = SHIPPING_FEE;
        int discount = -cartGlobal.calDiscount();
        int total = subtotal + shippingFee + discount;

        cart_checkout_subtotal_text.setText(Utils.currency(subtotal));
        cart_checkout_shipping_text.setText(Utils.currency(shippingFee));
        cart_checkout_discount_text.setText(Utils.currency(discount));
        cart_checkout_total_text.setText(Utils.currency(total));
    }
}