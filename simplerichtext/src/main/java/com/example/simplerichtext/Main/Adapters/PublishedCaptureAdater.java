package com.example.simplerichtext.Main.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplerichtext.Main.Holders.PublihedCaptureHolder;
import com.example.simplerichtext.R;

public class PublishedCaptureAdater extends RecyclerView.Adapter<PublihedCaptureHolder>{

    private Context mContext;

    public PublishedCaptureAdater(Context context) {
        mContext = context;
    }

    @Override
    public PublihedCaptureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.simple_layout_published_capture_item,
                parent,false);
        PublihedCaptureHolder holder = new PublihedCaptureHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PublihedCaptureHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
