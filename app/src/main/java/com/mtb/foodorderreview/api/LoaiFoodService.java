package com.mtb.foodorderreview.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtb.foodorderreview.model.Food;
import com.mtb.foodorderreview.model.LoaiFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface LoaiFoodService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();

    LoaiFoodService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.7:8085/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(LoaiFoodService.class);
    @GET("loaifood")
    Call<List<LoaiFood>> getAllFood();
}
