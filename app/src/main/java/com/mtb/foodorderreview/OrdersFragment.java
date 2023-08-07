package com.mtb.foodorderreview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.mtb.foodorderreview.api.ChiTietDonHangService;
import com.mtb.foodorderreview.api.DonHangService;
import com.mtb.foodorderreview.api.NhaHangService;
import com.mtb.foodorderreview.global.CartFood;
import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.homeview.OrderListViewAdapter;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.model.NhaHang;
import com.mtb.foodorderreview.restaurentview.RestaurantFood;
import com.mtb.foodorderreview.something.Order;
import com.mtb.foodorderreview.utils.ICallback;
import com.mtb.foodorderreview.utils.ICartFoodDetailFromAPI;
import com.mtb.foodorderreview.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    Context context;
    ListView orders_listview;
    List<CartFood> list;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
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
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        context = getContext();

        initialization(view);
        setOrderListV(view);
        return view;
    }

    private void initialization(View view) {
        orders_listview = view.findViewById(R.id.orders_listview);

        list = new ArrayList<>();
    }

    private RestaurantFood getRestFoodById(int id) {
        RestaurantFood f = new RestaurantFood(id, "Sample food " + id, "Sample description", Utils.URL_SAMPLE_IMAGE,
                15000);
        return f;
    }

    private Restaurant getRestaurantById(int id) {

        Restaurant f = new Restaurant(id, "Restaurant name " + id, Utils.URL_SAMPLE_IMAGE, "Restaurant address");
        return f;
    }

    private Restaurant converst(NhaHang nhaHang) {
        return null;
    }

    private CartFood getCardFoodByFoodId(int id, int quantity) {
        CartFood f = new CartFood(getRestFoodById(id), quantity);


        return f;
    }

    private List<CartFood> getCardFoodsByDetail(List<ICartFoodDetailFromAPI> cartFoodDetails) {
        List<CartFood> f = new ArrayList<>();

        for (ICartFoodDetailFromAPI c : cartFoodDetails) {
            CartFood cartFood = getCardFoodByFoodId(c.foodId(), c.quantity());
            f.add(cartFood);
        }

        return f;
    }

    private Order makeOrder(int id, int restaurantId, int state, String createdAt, List<ICartFoodDetailFromAPI> cartFoodDetails, int finalPrice) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        List<CartFood> cartFood = getCardFoodsByDetail(cartFoodDetails);

        Order o = null;
        try {
            o = new Order(id,
                    restaurant,
                    Utils.dateParse(createdAt),
                    cartFood,
                    Order.STATE.getState(state),
                    finalPrice);
        } catch (ParseException e) {
        }

        return o;
    }

    private int getFinalPrice(List<ICartFoodDetailFromAPI> list) {
        int total = 0;
        for (ICartFoodDetailFromAPI i : list) {
            Double val = i.price();
            total += val.intValue();
        }
        return total;
    }

    private ICallback callback;

    private void setOrderListV(View view) {
        List<Order> orderList = new ArrayList<>();
        OrderListViewAdapter adapter = new OrderListViewAdapter(context, orderList);


        DonHangService.apiService.getDonHangUI(UserGlobal.getInstance().getId()).enqueue(new Callback<List<Object[]>>() {
            @Override
            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                List<Object[]> list = response.body();
                if (list != null) {
                    for (Object[] objects : list) {
                        List<ICartFoodDetailFromAPI> l = new ArrayList<>();
                        final int[] sizeChiTietDonhang = {-1};

                        Double idDonHang = (Double) objects[0];
                        ChiTietDonHangService.apiService.listChiTietDonHangByDonhang(idDonHang.intValue()).enqueue(new Callback<List<Object[]>>() {
                            @Override
                            public void onResponse(Call<List<Object[]>> call, Response<List<Object[]>> response) {
                                List<Object[]> res = response.body();

                                sizeChiTietDonhang[0] = res.size();
                                if (res != null) {
                                    for (Object[] obj : res) {
                                        ICartFoodDetailFromAPI item = new ICartFoodDetailFromAPI() {

                                            @Override
                                            public int foodId() {
                                                Double idfood = (Double) obj[0];
                                                return idfood.intValue();
                                            }

                                            @Override
                                            public int quantity() {
                                                Double quantity = (Double) obj[1];
                                                return quantity.intValue();
                                            }

                                            @Override
                                            public double price() {
                                                return (double) obj[2];
                                            }
                                        };
                                        l.add(item);
                                    }
                                    Double num1 = (Double) objects[0];
                                    Double num2 = (Double) objects[1];
                                    Double num3 = (Double) objects[2];

                                    String year = objects[3].toString().substring(0, 4);
                                    String month = objects[3].toString().substring(5, 7);
                                    String date = objects[3].toString().substring(8, 10);

                                    String createDate = date.concat("/").concat(month).concat("/").concat(year);

                                    NhaHangService.apiService.getNhaHangById(num2.intValue()).enqueue(new Callback<NhaHang>() {
                                        @Override
                                        public void onResponse(Call<NhaHang> call, Response<NhaHang> response) {
                                            NhaHang n = response.body();
                                            Restaurant f = new Restaurant(n.getId(), n.getTen(), n.getAvatar(), n.getDiaChi());
                                            List<CartFood> cartFood = getCardFoodsByDetail(l);
                                            Order o = null;
                                            try {
                                                o = new Order(num1.intValue(),
                                                        f,
                                                        Utils.dateParse(createDate),
                                                        cartFood,
                                                        Order.STATE.getState(num2.intValue()),
                                                        getFinalPrice(l));
                                            } catch (ParseException e) {
                                                throw new RuntimeException(e);
                                            }
                                            orderList.add(o);
                                            adapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onFailure(Call<NhaHang> call, Throwable t) {

                                        }
                                    });

                                }

                            }

                            @Override
                            public void onFailure(Call<List<Object[]>> call, Throwable t) {

                            }
                        });
                    }


                }

            }

            @Override
            public void onFailure(Call<List<Object[]>> call, Throwable t) {

            }
        });
        orders_listview.setAdapter(adapter);

    }
}