package com.vince.imageloaderexp.api;

import com.vince.imageloaderexp.data.TngouVo;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by vince on 16/8/11.
 */

public interface TngouApiService {

    @GET("list")
    Observable<TngouVo> getData();

    @GET("https://kyfw.12306.cn/otn/")
    Observable<Object> get12306();
}
