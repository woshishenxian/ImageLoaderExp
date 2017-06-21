package com.vince.imageloaderexp.main;

import android.content.pm.PackageManager;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.Toast;

import com.alipay.euler.andfix.patch.PatchManager;
import com.vince.imageloaderexp.R;
import com.vince.imageloaderexp.data.Tngou;
import com.vince.imageloaderexp.data.TngouVo;
import com.vince.imageloaderexp.data.source.TngouRepository;
import com.vince.imageloaderexp.main.adapter.TngouAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TngouContract.View{

    RecyclerView mRecyclerView;

    private TngouPresenter mTngouPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        RecyclerView.LayoutManager manager = new GridLayoutManager(this,3);

        mRecyclerView.setLayoutManager(manager);

        mTngouPresenter = new TngouPresenter(this, TngouRepository.getInstance());


        EventBus.getDefault().register(this);

    }

    private synchronized void show(String content){
        new HandlerThread("t").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }


    @Override
    protected void onResume() {
        super.onResume();
//        mTngouPresenter.loadTngouData();
        mTngouPresenter.load12306Data();
    }

    @Override
    public void onUpdateSuccess(List<Tngou> tngous) {
        TngouAdapter tngouAdapter = new TngouAdapter(this,tngous);
        mRecyclerView.setAdapter(tngouAdapter);

        Integer in = 0;

        in.equals(1);

    }





    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void showLoading() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void onUpdateFailed(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(TngouContract.Presenter presenter) {

    }

    @Override
    public void onUpdateSuccess(String content) {
        Toast.makeText(this,content,Toast.LENGTH_LONG).show();
    }
}
