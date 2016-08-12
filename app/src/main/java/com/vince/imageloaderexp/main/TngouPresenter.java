package com.vince.imageloaderexp.main;

import android.support.annotation.NonNull;

import com.vince.imageloaderexp.data.Tngou;
import com.vince.imageloaderexp.data.source.TngouDataSource;
import com.vince.imageloaderexp.data.source.TngouRepository;

import java.util.List;


/**
 * Created by vince on 16/8/12.
 */

public class TngouPresenter implements TngouContract.Presenter {

    private TngouContract.View mTngouView;

    private TngouRepository mTngouRepository;


    public TngouPresenter(@NonNull TngouContract.View mTngouView, @NonNull  TngouRepository mTngouRepository) {
        this.mTngouView = mTngouView;
        this.mTngouRepository = mTngouRepository;
    }

    @Override
    public void loadTngouData() {
        if(!mTngouView.isActive()){
            return;
        }
        mTngouView.showLoading();
        mTngouRepository.loadTngouData(new TngouDataSource.LoadTngouCallBack() {
            @Override
            public void onTasksLoaded(List<Tngou> tasks) {
                mTngouView.onUpdateSuccess(tasks);
                mTngouView.dismissLoading();
            }

            @Override
            public void onDataNotAvailable(String msg) {
                mTngouView.dismissLoading();
                mTngouView.onUpdateFailed(msg);
            }
        });
    }

}
