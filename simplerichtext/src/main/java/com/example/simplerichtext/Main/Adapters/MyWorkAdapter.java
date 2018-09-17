package com.example.simplerichtext.Main.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Holders.MyWorkHolder;
import com.example.simplerichtext.R;

import java.util.List;

public class MyWorkAdapter extends RecyclerView.Adapter<MyWorkHolder> {

    private Fragment mContext;
    private List<MyPublishModule> mDatas;

    public MyWorkAdapter(Fragment context, List<MyPublishModule> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public MyWorkHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext.getContext()).
                inflate(R.layout.simple_layout_main_recycler_item,parent,false);

        return new MyWorkHolder(mContext,view);
    }

    @Override
    public void onBindViewHolder(MyWorkHolder holder, int position) {

        holder.setData(mDatas.get(position));

    }


    @Override
    public int getItemCount() {

        return mDatas.size();
    }
}
