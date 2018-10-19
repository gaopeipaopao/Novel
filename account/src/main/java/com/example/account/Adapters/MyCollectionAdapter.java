package com.example.account.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.account.Holders.MyCollectionHolder;
import com.example.account.Models.MyCollectionModel;
import com.example.account.R;

import java.util.List;
import java.util.zip.Inflater;

public class MyCollectionAdapter extends RecyclerView.Adapter<MyCollectionHolder> {

    private Context mContext;
    private List<MyCollectionModel> mDatas;

    public MyCollectionAdapter(Context context, List<MyCollectionModel> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public MyCollectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.account_layout_mycollection_item,parent,false);


        return new MyCollectionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCollectionHolder holder, int position) {

        holder.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
