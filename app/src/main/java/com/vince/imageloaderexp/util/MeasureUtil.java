package com.vince.imageloaderexp.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by admin on 17/4/8.
 */
public class MeasureUtil {

    public static int[] getScreenSize(Activity activity){


        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return new int[]{displayMetrics.widthPixels,displayMetrics.heightPixels};

    }
}
