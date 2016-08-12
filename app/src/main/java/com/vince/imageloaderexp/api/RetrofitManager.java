package com.vince.imageloaderexp.api;

import java.util.Observable;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vince on 16/8/11.
 */

public class RetrofitManager {

    Retrofit retrofit;

    private static class RetrofitManagerHolder{
        static final RetrofitManager instance = new RetrofitManager();

    }

    private RetrofitManager() {
        initRetrofit();
    }

    public static RetrofitManager getInstance(){
        return RetrofitManagerHolder.instance;
    }

    public Retrofit getRetrofit(){
        if(retrofit == null)
            initRetrofit();
        return retrofit;
    }

    private void initRetrofit(){
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.tngou.net/tnfs/api/").client(new OkHttpClient.Builder().build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }


    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }


}
