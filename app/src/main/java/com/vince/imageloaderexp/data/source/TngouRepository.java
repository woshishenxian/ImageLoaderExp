package com.vince.imageloaderexp.data.source;

import android.support.annotation.NonNull;

import com.vince.imageloaderexp.api.TngouApiService;
import com.vince.imageloaderexp.data.TngouVo;
import com.vince.imageloaderexp.data.source.impl.TngouDataSourceImpl;

import rx.Observable;

/**
 * Created by vince on 16/8/12.
 */

public class TngouRepository implements TngouDataSource{

    private static TngouRepository INSTANCE;

    TngouDataSourceImpl mTngouDataSourceImpl;


    public static TngouRepository getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new TngouRepository();
        }
        return INSTANCE;
    }

    private TngouRepository() {
        mTngouDataSourceImpl = new TngouDataSourceImpl();
    }

    @Override
    public void loadTngouData(@NonNull LoadTngouCallBack callBack) {
        mTngouDataSourceImpl.loadTngouData(callBack);
    }

    @Override
    public void load12306(@NonNull Load12306Back callBack) {
        mTngouDataSourceImpl.load12306(callBack);
    }
}
