package com.mtb.foodorderreview.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtb.foodorderreview.model.UuDai;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UuDaiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();

    UuDaiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.104:8081/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(UuDaiService.class);
    @GET("uudai/nhahang/{id}/")
    Call<List<UuDai>> getListUuDaiByNhaHang(@Path("id")int id);
}
