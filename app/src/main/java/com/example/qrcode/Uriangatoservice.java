package com.example.qrcode;

import java.lang.annotation.Target;
import java.net.URL;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Uriangatoservice {
    public String BASE_URL= "http://192.168.11.59:8080/urbano1.4/";
    public String HASH_KEY = "encrypted";
    public String HASH_VALUE = "906B8658D0DCF767B9B06FE7D5E2ADB3";

    @GET("core/api/construcion-api.php")
    Call<Licencia> getLicencia();

}
