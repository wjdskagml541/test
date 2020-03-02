package com.azure.partnercenter;

import java.util.concurrent.TimeUnit;

import com.azure.partnercenter.auth.AuthorizationApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    public static AuthorizationApi createAuthorization(final String baseURL) {
        Retrofit retrofit = createRetrofit(baseURL);
        return retrofit.create(AuthorizationApi.class);
    }


    private static Retrofit createRetrofit(final String baseURL) {
        OkHttpClient okHttpClient = createHttpClient();

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private static OkHttpClient createHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

}
