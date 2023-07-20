package com.mtb.foodorderreview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        initialization();
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

    private void updateStatePreparing() {
        delivery_fake_line_shipping
    }

    public void updateUI(Order.STATE state) {
        switch (state) {
            case PENDING:
                break;
            case PREPARING:
                break;
            case DELIVERED:
                break;
        }
    }
}