package com.vince.imageloaderexp.data.source;

import android.support.annotation.NonNull;

import com.vince.imageloaderexp.data.Tngou;

import java.util.List;

/**
 * Created by vince on 16/8/12.
 */

public interface TngouDataSource {

    interface LoadTngouCallBack{
        void onTasksLoaded(List<Tngou> tasks);

        void onDataNotAvailable(String msg);
    }

    void loadTngouData(@NonNull LoadTngouCallBack callBack);

}
