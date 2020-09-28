package com.example.qrcode;

import java.lang.annotation.Target;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Uriangatoservice {
    public String BASE_URL= "http://192.168.11.59:8080/urbano1.5/core/api/";
    public String HASH_KEY = "encrypted";

    @GET("construcion-api.php")
    Call<Licencia> getLicencia();

    @Headers("Content-Type: application/json")
    @POST("logApiJwt.php")
    Call<ResponseBody> getLogin(@Body ApiUsuario apiusuario);

}
