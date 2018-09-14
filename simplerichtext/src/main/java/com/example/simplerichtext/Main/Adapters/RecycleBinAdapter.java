package com.example.simplerichtext.Main.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basecomponent.Modules.WriteModule;
import com.example.simplerichtext.Main.Holders.RecycleBinHolder;
import com.example.simplerichtext.R;

import java.util.List;

public class RecycleBinAdapter extends RecyclerView.Adapter<RecycleBinHolder> {


    private Context mContext;
    private  List<WriteModule> mDatas;

    public RecycleBinAdapter(Context context,List<WriteModule> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public RecycleBinHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.simple_layout_recycle_bin_item,parent,false);
        RecycleBinHolder holder = new RecycleBinHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleBinHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
