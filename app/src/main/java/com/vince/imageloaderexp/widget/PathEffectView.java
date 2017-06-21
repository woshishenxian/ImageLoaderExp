package com.vince.imageloaderexp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 17/4/8.
 */
public class PathEffectView extends View{

    private float mPhase;
    private Paint mPaint;
    private Path mPath;
    private PathEffect[] mEffects;




    public PathEffectView(Context context) {
        this(context,null);
    }

    public PathEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);


        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.DKGRAY);

        //实例化路径
        mPath = new Path();

        mPath.moveTo(0,0);

        // 定义路径的各个点
        for (int i = 0; i <= 30; i++) {
            mPath.lineTo(i * 35, (float) (Math.random() * 100));
        }

        // 创建路径效果数组
        mEffects = new PathEffect[7];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

         /*
         * 实例化各类特效
         */
        mEffects[0] = null;
        mEffects[1] = new CornerPathEffect(10);
        mEffects[2] = new DiscretePathEffect(3.0F, 5.0F);
        mEffects[3] = new DashPathEffect(new float[] { 20, 10, 5, 10 }, mPhase);
        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);
        mEffects[4] = new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE);
        mEffects[5] = new ComposePathEffect(mEffects[2], mEffects[4]);
        mEffects[6] = new SumPathEffect(mEffects[4], mEffects[3]);

        /*
         * 绘制路径
         */
        for (int i = 0; i < mEffects.length; i++) {
            mPaint.setPathEffect(mEffects[i]);
            canvas.drawPath(mPath, mPaint);

            // 每绘制一条将画布向下平移250个像素
            canvas.translate(0, 250);
        }

        // 刷新偏移值并重绘视图实现动画效果
        mPhase += 1;
        invalidate();
    }
}
