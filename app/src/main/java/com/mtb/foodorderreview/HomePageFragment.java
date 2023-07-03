package com.mtb.foodorderreview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.mtb.foodorderreview.homeview.HomeFoodType;
import com.mtb.foodorderreview.homeview.HomeFoodTypeGridAdapter;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        HomeFoodTypeUI(view, getContext());

        return view;
    }

    private void HomeFoodTypeUI(View view, Context context) {

        HomeFoodType[] l = {
                new HomeFoodType("Rice", R.drawable.icon_food_type_rice),
                new HomeFoodType("Rice2", R.drawable.icon_food_type_rice),
                new HomeFoodType("Rice3", R.drawable.icon_food_type_rice),
                new HomeFoodType("Rice4", R.drawable.icon_food_type_rice),
                new HomeFoodType("Rice5", R.drawable.icon_food_type_rice)
        };


        HomeFoodTypeGridAdapter adapter = new HomeFoodTypeGridAdapter(context, Arrays.asList(l));

        GridView gridView = view.findViewById(R.id.home_food_type_grid_1);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long
                    id) {
                Object o = gridView.getItemAtPosition(position);
                HomeFoodType foodType = (HomeFoodType) o;
                Toast.makeText(context, "Selected :" + " " + foodType.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}