package com.mtb.foodorderreview.utils;

import android.view.View;

public interface ItemClickListener<T> {
    void onClick(View view, int position, boolean isLongClick, T item);
}
