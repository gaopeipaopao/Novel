package com.example.gaope.novel.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseHolder extends RecyclerView.ViewHolder {

    public BaseHolder(View itemView) {
        super(itemView);

    }

    public abstract void setData();

}
