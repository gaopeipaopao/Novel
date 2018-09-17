package com.example.simplerichtext.Main.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basecomponent.Modules.WriteModule;
import com.example.simplerichtext.Main.Holders.DraftBoxHolder;
import com.example.simplerichtext.R;

import java.util.List;

public class DraftBoxAdapter extends RecyclerView.Adapter<DraftBoxHolder> {
    private Context mContext;
    private List<WriteModule> mDatas;


    public DraftBoxAdapter(Context context,List<WriteModule> data) {
        mContext = context;
        mDatas = data;
    }

    @Override
    public DraftBoxHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.simple_layout_draftbox_item,parent,false);
        DraftBoxHolder holder = new DraftBoxHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(DraftBoxHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
