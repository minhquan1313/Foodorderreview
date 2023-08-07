package com.mtb.foodorderreview.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtb.foodorderreview.model.NhaHangFood;
import com.mtb.foodorderreview.utils.Utils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NhaHangFoodService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();

    NhaHangFoodService apiService = new Retrofit.Builder()
            .baseUrl(Utils.ip)
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(NhaHangFoodService.class);

    @POST("/nha_hang_food/id")
    Call<NhaHangFood> getIDNhaHangFood(@Body Map<String, Object> obj);
}
