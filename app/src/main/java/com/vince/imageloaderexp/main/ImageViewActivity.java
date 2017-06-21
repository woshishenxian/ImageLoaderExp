package com.vince.imageloaderexp.main;

import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.vince.imageloaderexp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by admin on 17/4/18.
 */
public class ImageViewActivity extends AppCompatActivity{


    private Button delBtn, saveBtn;
    private ImageView iv_canvas;



    private Paint mPaint;
    private Canvas canvas;
    private Bitmap baseBitmap;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_img);
        init();
        iv_canvas = (ImageView) findViewById(R.id.iv_canvas);
        delBtn = (Button) findViewById(R.id.del_btn);
        saveBtn = (Button) findViewById(R.id.save_btn);

        iv_canvas.setOnTouchListener(new View.OnTouchListener() {

            private float startX;
            private float startY;



            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        startX = event.getX();
                        startY = event.getY();

                        if (baseBitmap == null){
                            baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                            canvas = new Canvas(baseBitmap);
                            canvas.drawColor(Color.WHITE);
                        }

                        break;
                    case MotionEvent.ACTION_MOVE:

                        float moveX = event.getX();
                        float moveY = event.getY();

                        canvas.drawLine(startX,startY,moveX,moveY,mPaint);

                        startX = moveX;
                        startY = moveY;

                        iv_canvas.setImageBitmap(baseBitmap);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }


                return true;
            }
        });
    }

    private void init(){

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);


    }


    public void del(View view){
        if (baseBitmap !=null){
            baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(baseBitmap);
            canvas.drawColor(Color.WHITE);

            iv_canvas.setImageBitmap(baseBitmap);
        }
    }

    public void save(View view){
        if (baseBitmap !=null){
            String path = getDiskChaheDir(this,"").getPath();
            Log.i("path",path);
            File f = new File(path, "tupian.png");
            if (f.exists()) {
                f.delete();
            }
            try {
                FileOutputStream out = new FileOutputStream(f);
                baseBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            SoundPool soundPool;



            Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
            vib.vibrate(1000);

        }
        }


    private File getDiskChaheDir(Context context, String uniqueName) {
        boolean externalStorageAvailable = Environment
                .getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvailable) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


}

