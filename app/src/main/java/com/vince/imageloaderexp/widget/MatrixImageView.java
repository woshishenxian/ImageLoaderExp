package com.vince.imageloaderexp.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.vince.imageloaderexp.R;
import com.vince.imageloaderexp.util.MeasureUtil;

/**
 * Created by admin on 17/4/9.
 */
public class MatrixImageView extends ImageView{

    private static final int MODE_NONE = 0x00123; //默认的触摸模式
    private static final int MODE_DRAG = 0x00321; //拖拽模式
    private static final int MODE_ZOOM = 0x00132; //缩放 or 旋转模式


    private int mode ; //当前模式

    private float preMove = 1F; //上一次手指移动的距离
    private float saveRotate = 0F; //保存了的角度值
    private float rotate = 0F; //旋转了的角度值

    private float[] preEventCoor ; //上一次各触摸点的位置集合

    private PointF start ,mid; //起点 、终点对象
    private Matrix currentMatrix,saveMatrix; //当前 、保存 的Matrix对象

    private Context mContext;


    public MatrixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        init();

    }

    private void init(){
        /**
         * 实例化对象
         */
        currentMatrix = new Matrix();
        saveMatrix = new Matrix();
        start = new PointF();
        mid = new PointF();


        //模式初始化
        mode = MODE_NONE;

        //设置图片资源
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_share_qq);

        int[] screenSize = MeasureUtil.getScreenSize((Activity) mContext);

        bitmap = Bitmap.createScaledBitmap(bitmap, screenSize[0] , screenSize[1],true);

        setImageBitmap(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                saveMatrix.set(currentMatrix);
                start.set(event.getX(),event.getY());
                mode = MODE_DRAG;
                preEventCoor = null;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                preMove = calSpecing(event);
                if (preMove > 10F){
                    saveMatrix.set(currentMatrix);
                    calMidPoint(mid,event);
                    mode = MODE_ZOOM;
                }
                preEventCoor = new float[4];
                preEventCoor[0] = event.getX(0);
                preEventCoor[1] = event.getX(1);
                preEventCoor[2] = event.getY(0);
                preEventCoor[3] = event.getY(1);
                saveRotate = calRotation(event);

                break;

            case MotionEvent.ACTION_UP:// 单点离开屏幕时
            case MotionEvent.ACTION_POINTER_UP:// 第二个点离开屏幕时
                mode = MODE_NONE;
                preEventCoor = null;
                break;

            case MotionEvent.ACTION_MOVE:
                /**
                 * 单点触控平移
                 *
                 */
                if (mode == MODE_DRAG){
                    currentMatrix.set(saveMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    currentMatrix.postTranslate(dx,dy);
                }
                /**
                 * 两点触控旋转
                 */
                else if (mode == MODE_ZOOM && event.getPointerCount() ==2){
                    float currentMove = calSpecing(event);
                    currentMatrix.set(saveMatrix);
                    /*
                     * 之间移动距离大于10F 缩放
                     */
                    if (currentMove > 10F){
                        float scale = currentMove / preMove;
                        currentMatrix.postScale(scale, scale, mid.x, mid.y);
                    }

                     /*
                 * 保持两点时旋转
                 */
                    if (preEventCoor != null) {
                        rotate = calRotation(event);
                        float r = rotate - saveRotate;
                        currentMatrix.postRotate(r, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
                    }
                }


                break;
        }

        setImageMatrix(currentMatrix);
        return true;
    }


    /**
     * 计算两个触摸点的距离
     * @param event
     * @return
     */
    private float calSpecing(MotionEvent event){

        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);

        return (float) Math.sqrt(x * x + y* y);

    }



    private void calMidPoint(PointF point,MotionEvent event){
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(0);

        point.set(x/2,y/2);
    }


    /**
     * 计算旋转角度
     *
     * @event 事件对象
     * @return 角度值
     */
    private float calRotation(MotionEvent event) {
        double deltaX = (event.getX(0) - event.getX(1));
        double deltaY = (event.getY(0) - event.getY(1));
        double radius = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radius);
    }

}
