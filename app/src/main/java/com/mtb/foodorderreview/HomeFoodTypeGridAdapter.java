package com.mtb.foodorderreview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HomeFoodTypeGridAdapter extends BaseAdapter {
    private Context context;

    private List<HomeFoodType> list;
    private LayoutInflater inflater;

    public HomeFoodTypeGridAdapter(Context context, List<HomeFoodType> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.home_food_type_grid_layout, null);

        TextView text = convertView.findViewById(R.id.home_food_type_grid_layout_text_1);
        text.setText(list.get(position).getName());

        ImageView img = convertView.findViewById(R.id.home_food_type_grid_layout_img_1);
        img.setImageResource(list.get(position).getImg());

        return convertView;
    }
}
