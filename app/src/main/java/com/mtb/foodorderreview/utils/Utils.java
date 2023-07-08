package com.mtb.foodorderreview.utils;


import android.content.res.Resources;

public class Utils {

    public static int pxToSp(float px, Resources getResources) {
        float scaledDensity = getResources.getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity);
    }
}
