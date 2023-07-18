package com.mtb.foodorderreview.utils;


import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static final String CURRENCY = "đ";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

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

    public static String currency(double number) {
        String format = "%,.2f";

        return String.format(Locale.getDefault(), format, number) + CURRENCY;
    }

    public static String currency(int number) {
        String format = "%,d";

        return String.format(Locale.getDefault(), format, number) + CURRENCY;
    }

    public static String dateStr(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date dateParse(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    public static class CommonUIFunction {
        public static void backBtn(Context context, LinearLayout btn) {
            btn.setOnClickListener(v -> ((Activity) context).finish());
        }
    }
}