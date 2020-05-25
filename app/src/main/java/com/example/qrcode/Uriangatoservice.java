package com.example.qrcode;

import java.lang.annotation.Target;
import java.net.URL;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Uriangatoservice {
    public String BASE_URL= "http://192.168.1.180/Dinnco/";
    public String HASH_KEY = "id_area";
    public String HASH_VALUE = "4";

    @GET("current_status_system.php")
    Call<Area> getLicencia();

}
