package com.vince.imageloaderexp.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.vince.imageloaderexp.R;

/**
 * Created by admin on 17/4/9.
 */
public class BrickView extends View{

    private Paint mFillPaint , mStrokePaint;

    private BitmapShader mBitmapShader;

    private float posX , posY;


    public BrickView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }


    private void initPaint(){

        //实例化描边画笔  并设置参数
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);

        mStrokePaint.setStrokeWidth(5);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(0xFF000000);


        //实例化填充画笔
        mFillPaint = new Paint();

        //实例化Shader
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_share_qq);
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        mFillPaint.setShader(mBitmapShader);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);
         /*
         * 绘制圆和描边
         */
        canvas.drawCircle(posX, posY, 300, mFillPaint);
        canvas.drawCircle(posX, posY, 300, mStrokePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_MOVE){
            posX = event.getX();
            posY = event.getY();

            invalidate();
        }

        return true;


    }
}
