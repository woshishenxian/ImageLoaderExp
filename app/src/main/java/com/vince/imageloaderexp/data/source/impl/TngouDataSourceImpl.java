package com.vince.imageloaderexp.data.source.impl;

import android.support.annotation.NonNull;

import com.vince.imageloaderexp.api.RetrofitManager;
import com.vince.imageloaderexp.api.TngouApiService;
import com.vince.imageloaderexp.data.Tngou;
import com.vince.imageloaderexp.data.TngouVo;
import com.vince.imageloaderexp.data.source.TngouDataSource;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by vince on 16/8/12.
 */

public class TngouDataSourceImpl implements TngouDataSource {

    TngouApiService service;


    public TngouDataSourceImpl() {
        service = RetrofitManager.getInstance().getRetrofit().create(TngouApiService.class);
    }

    @Override
    public void loadTngouData(@NonNull final LoadTngouCallBack callBack) {


        service.getData()
                .filter(new Func1<TngouVo, Boolean>() {
                    @Override
                    public Boolean call(TngouVo tngouVo) {
                        return tngouVo.isStatus();
                    }
                })
                .filter(new Func1<TngouVo, Boolean>() {
                    @Override
                    public Boolean call(TngouVo tngouVo) {
                        return tngouVo.isStatus();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TngouVo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onDataNotAvailable(e.getMessage());
                    }

                    @Override
                    public void onNext(TngouVo tngouVo) {
                        if(tngouVo.isStatus()){
                            callBack.onTasksLoaded(tngouVo.getTngou());
                        }else{
                            callBack.onDataNotAvailable("数据加载失败");
                        }

                    }
                });


        Schedulers.io();
        Schedulers.newThread();

    }



    @Override
    public void load12306(@NonNull final Load12306Back callBack) {
        service.get12306().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        callBack.onTasksLoaded("请求成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onDataNotAvailable("请求失败");
                    }

                    @Override
                    public void onNext(Object o) {
                        callBack.onTasksLoaded("请求成功");
                    }
                });
    }
}
