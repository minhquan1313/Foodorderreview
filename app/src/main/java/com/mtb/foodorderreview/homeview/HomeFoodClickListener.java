package com.mtb.foodorderreview.homeview;

import android.view.View;

public interface HomeFoodClickListener {
    void onClick(View view, int position, boolean isLongClick, HomeFood homeFood);
}
