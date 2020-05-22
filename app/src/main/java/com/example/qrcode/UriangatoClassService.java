package com.example.qrcode;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UriangatoClassService {
    public static final String TAG = "TAG";
    public static Uriangatoservice getService(){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalUrl = originalRequest.url();
                HttpUrl httpUrl = originalUrl.newBuilder()
                        .addQueryParameter(Uriangatoservice.HASH_KEY,Uriangatoservice.HASH_VALUE)
                        .build();

                Request.Builder requester = originalRequest.newBuilder().url(httpUrl);
                Request request = requester.build();

                return chain.proceed(request);
            }
        }).build();

        return new Retrofit.Builder()
                .baseUrl(Uriangatoservice.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Uriangatoservice.class);
    }
}
