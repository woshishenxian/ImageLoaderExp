package com.vince.imageloaderexp.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.vince.imageloaderexp.R;
import com.vince.imageloaderexp.data.Tngou;
import com.vince.imageloaderexp.library.ImageLoader;
import com.vince.imageloaderexp.widget.SquareImageView;

import java.util.List;

/**
 * Created by vince on 16/8/12.
 */

public class TngouAdapter extends RecyclerView.Adapter<TngouAdapter.ViewHolder>{

    private Context context;
    private List<Tngou> data;

    public TngouAdapter(Context context, List<Tngou> data) {
        this.context = context;
        this.data=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String url = data.get(position).getImg();
//        Glide.with(context).load(url).into(holder.mSquareImageView);
        ImageLoader.build(context).bindBitmap(url,holder.mSquareImageView,100,100);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        SquareImageView mSquareImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mSquareImageView = (SquareImageView) itemView.findViewById(R.id.mSquareImageView);
        }
    }
}
