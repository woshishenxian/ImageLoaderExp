package com.vince.imageloaderexp.main.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.Collection;

/**
 * Created by admin on 17/4/13.
 */
public class ListViewAdapter extends BaseAdapter{
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public ListViewAdapter() {
    }


    public void updateItem(ListView listView, long id){


        int start = listView.getFirstVisiblePosition();
        int end = listView.getLastVisiblePosition();

        for (int j = start; j < end; j++) {
            if (id == listView.getItemAtPosition(j)){
                View view = listView.getChildAt(j - start);
                getView(j, view, listView);
                break;
            }
        }


    }
}
