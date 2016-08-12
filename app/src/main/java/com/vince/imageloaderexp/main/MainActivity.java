package com.vince.imageloaderexp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.vince.imageloaderexp.R;
import com.vince.imageloaderexp.data.Tngou;
import com.vince.imageloaderexp.data.source.TngouRepository;
import com.vince.imageloaderexp.main.adapter.TngouAdapter;

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
    }


    @Override
    protected void onResume() {
        super.onResume();
        mTngouPresenter.loadTngouData();
    }

    @Override
    public void onUpdateSuccess(List<Tngou> tngous) {
        mRecyclerView.setAdapter(new TngouAdapter(this,tngous));

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
}
