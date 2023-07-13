package com.mtb.foodorderreview.utils;


import android.content.Context;
import android.content.res.Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static int pxToSp(float px, Resources getResources) {
        float scaledDensity = getResources.getDisplayMetrics().scaledDensity;
        return (int) (px / scaledDensity);
    }

    public static void writeFile(Context context, String fileName, String text) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, context.MODE_APPEND);

            outputStream.write(text.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        } catch (Exception e) {
        }
    }

    public static String readFile(Context context, String fileName, String text) {
        try {
            FileInputStream inputStream = null;
            inputStream = context.openFileInput(fileName);

            int c;
            StringBuilder temp = new StringBuilder();

            while ((c = inputStream.read()) != -1) {
                temp.append(Character.toString((char) c));
            }

            inputStream.close();
            return temp.toString();
        } catch (Exception e) {
        }

        return "";
    }
}
