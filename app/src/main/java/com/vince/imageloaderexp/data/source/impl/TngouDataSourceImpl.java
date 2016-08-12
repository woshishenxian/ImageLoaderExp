package com.vince.imageloaderexp.data.source.impl;

import android.support.annotation.NonNull;

import com.vince.imageloaderexp.api.RetrofitManager;
import com.vince.imageloaderexp.api.TngouApiService;
import com.vince.imageloaderexp.data.TngouVo;
import com.vince.imageloaderexp.data.source.TngouDataSource;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
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
        service.getData().subscribeOn(Schedulers.io())
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
    }
}