package com.mtb.foodorderreview.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtb.foodorderreview.model.ChiTietDonHang;
import com.mtb.foodorderreview.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChiTietDonHangService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();

    ChiTietDonHangService apiService = new Retrofit.Builder()
            .baseUrl(Utils.ip)
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(ChiTietDonHangService.class);

    @POST("chi_tiet/list")
    Call<List<ChiTietDonHang>> saveListChiTietDonHang(@Body List<ChiTietDonHang> list);

    @GET("chi_tiet/donhang/{id}")
    Call<List<Object[]>> listChiTietDonHangByDonhang(@Path("id") int id);
}
