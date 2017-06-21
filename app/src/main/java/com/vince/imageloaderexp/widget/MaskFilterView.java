package com.vince.imageloaderexp.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.vince.imageloaderexp.util.MeasureUtil;

/**
 * Created by admin on 17/4/8.
 */
public class MaskFilterView extends View {

    private static final int RECT_SIZE = 800;

    private Paint mPaint;
//    private Context mContext;

    private int left, top, right, bottom;//


    public MaskFilterView(Context context) {
        this(context,null);
    }

    public MaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
        initRes(context);

        setLayerType(LAYER_TYPE_SOFTWARE,null);
    }

    private void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG| Paint.DITHER_FLAG);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xFF603811);


        mPaint.setMaskFilter(new BlurMaskFilter(20,BlurMaskFilter.Blur.SOLID));
    }

    /**
     * 初始化资源
     */
    private void initRes(Context mContext) {
        /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         */
        left = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - RECT_SIZE / 2;
        top = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - RECT_SIZE / 2;
        right = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 + RECT_SIZE / 2;
        bottom = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 + RECT_SIZE / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);

        canvas.drawRect(left,top,right,bottom,mPaint);

    }
}
