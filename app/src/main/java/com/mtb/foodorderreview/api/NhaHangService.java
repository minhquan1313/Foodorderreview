package com.mtb.foodorderreview.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtb.foodorderreview.model.NhaHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NhaHangService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();

    NhaHangService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.104:8081/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(NhaHangService.class);
    @GET("nhahang/")
    Call<List<NhaHang>> getListNH();

    @GET("nhahang/rating/{id}")
    Call<Double> getRatingOfRestaurant(@Path("id") int id);

    @GET("nhahang/countrating/{id}")
    Call<Integer> getCountOfRate(@Path("id") int id);
}
