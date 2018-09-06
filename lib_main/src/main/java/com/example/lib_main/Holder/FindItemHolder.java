package com.example.lib_main.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_main.R;


public class FindItemHolder extends BaseHolder {
    private View mView;
    private ImageView mBookBg;
    private TextView mBookUser;
    private TextView mBookName;
    private TextView mUpdateNow;
    private TextView mBrief;
    private TextView mStartWork;

    public FindItemHolder(View itemView) {
        super(itemView);
        mView = itemView;

        mBookBg = (ImageView) (mView.findViewById(R.id.iv_book_bg));
        mBookName = (TextView) (mView.findViewById(R.id.tv_bookname));
//        mBookUser = (TextView)(mView.findViewById(R.id.tv_bookuser));
//        mUpdateNow = (TextView)(mView.findViewById(R.id.tv_updatenow));
//        mBrief = (TextView)(mView.findViewById(R.id.tv_book_brief));
//        mStartWork = (TextView)(mView.findViewById(R.id.tv_start_work));
    }

    @Override
    public void setData() {

    }
}
