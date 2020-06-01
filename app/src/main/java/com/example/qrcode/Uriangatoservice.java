package com.example.qrcode;

import java.lang.annotation.Target;
import java.net.URL;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Uriangatoservice {
    public String BASE_URL= "http://192.168.11.59:8080/urbano1.5/core/api/";
    public String HASH_KEY = "encrypted";

    @GET("construcion-api.php")
    Call<Licencia> getLicencia();

}
