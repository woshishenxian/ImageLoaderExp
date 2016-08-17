package com.vince.imageloaderexp;

import android.app.Application;

import com.vince.imageloaderexp.api.HttpsUtils;

import java.io.InputStream;

/**
 * Created by admin on 16/8/14.
 */
public class IApplicaiton extends Application{

    public  static HttpsUtils.SSLParams sslParams;

    @Override
    public void onCreate() {
        super.onCreate();
        sslParams =
                HttpsUtils.getSslSocketFactory(new InputStream[]{getResources().openRawResource(R.raw.srca)},null,null);
    }
}
