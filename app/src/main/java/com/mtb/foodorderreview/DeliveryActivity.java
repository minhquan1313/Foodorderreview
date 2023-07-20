package com.mtb.foodorderreview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.mtb.foodorderreview.global.OrderGlobal;
import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.something.Order;

public class DeliveryActivity extends AppCompatActivity {
    View delivery_fake_line_prepare,
            delivery_fake_line_shipping,
            delivery_fake_line_shipped;
    Button delivery_fake_submit_btn;
    ImageView delivery_icon_image_prepare,
            delivery_icon_image_shipping,
            delivery_icon_image_shipped;
    TextView delivery_status_text,
            delivery_restaurant_text,
            delivery_restaurant_address_text,
            delivery_delivery_location_text,
            delivery_delivery_location_address_text;

    OrderGlobal orderGlobal = OrderGlobal.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        initialization();

        updateUI(orderGlobal.getOrder().getState());
        updateStateDelivering();
    }

    private void initialization() {
        delivery_fake_line_prepare = findViewById(R.id.delivery_fake_line_prepare);
        delivery_fake_line_shipping = findViewById(R.id.delivery_fake_line_shipping);
        delivery_fake_line_shipped = findViewById(R.id.delivery_fake_line_shipped);
        delivery_fake_submit_btn = findViewById(R.id.delivery_fake_submit_btn);
        delivery_icon_image_prepare = findViewById(R.id.delivery_icon_image_prepare);
        delivery_icon_image_shipping = findViewById(R.id.delivery_icon_image_shipping);
        delivery_icon_image_shipped = findViewById(R.id.delivery_icon_image_shipped);
        delivery_status_text = findViewById(R.id.delivery_status_text);
        delivery_restaurant_text = findViewById(R.id.delivery_restaurant_text);
        delivery_restaurant_address_text = findViewById(R.id.delivery_restaurant_address_text);
        delivery_delivery_location_text = findViewById(R.id.delivery_delivery_location_text);
        delivery_delivery_location_address_text = findViewById(R.id.delivery_delivery_location_address_text);
    }


    public void updateUI(Order.STATE state) {

        delivery_delivery_location_address_text.setText(UserGlobal.getInstance().getAddress());

        switch (state) {
            case PENDING:
                updateStatePending();

                break;
            case PREPARING:
                updateStatePreparing();
            case DELIVERING:
                updateStateDelivering();
                break;
            case DELIVERED:
                updateStateDelivered();

                break;
        }
    }

    private void updateStatePending() {

    }

    private void updateStatePreparing() {
        delivery_status_text.setText("Nhà hàng đang chuẩn bị");

    }

    private void updateStateDelivering() {
        updateStatePreparing();

        delivery_status_text.setText("Shipper đang giao");
        delivery_fake_line_shipping.setBackgroundResource(R.color.primary);
        delivery_icon_image_shipping.setColorFilter(ContextCompat.getColor(this, R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
        delivery_icon_image_shipping.setBackgroundResource(R.drawable.shape_border_box_primary);
    }

    private void updateStateDelivered() {
    }
}