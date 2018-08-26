package com.example.gaope.novel.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaope.novel.Holder.BaseHolder;
import com.example.gaope.novel.Holder.BookItemHolder;
import com.example.gaope.novel.Holder.FindItemHolder;
import com.example.gaope.novel.R;

public class FindRecyclerAdapter extends RecyclerView.Adapter<BaseHolder> {

    private Context mContext;

    public FindRecyclerAdapter(Context context) {
        this.mContext = context;
    }
    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_find_recycler,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mContext.startActivity(new Intent(mContext, ReadActivity.class));
                //
                }
        });
        return new FindItemHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }

}
