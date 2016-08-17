package com.vince.imageloaderexp.main;

import com.vince.imageloaderexp.BasePresenter;
import com.vince.imageloaderexp.BaseView;
import com.vince.imageloaderexp.data.Tngou;

import java.util.List;

/**
 * Created by vince on 16/8/12.
 */

public interface TngouContract {

    interface View extends BaseView<Presenter> {

        void onUpdateSuccess(List<Tngou> tngous);
        void onUpdateSuccess(String content);

        boolean isActive();

        void showLoading();

        void dismissLoading();

        void onUpdateFailed(String msg);

    }

    interface Presenter extends BasePresenter {
       void loadTngouData();
        void load12306Data();
    }
}
