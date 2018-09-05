package com.example.simplerichtext.Main.Holders;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Main.Activities.NovelCaptureActivity;
import com.example.simplerichtext.R;

public class MyWorkHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private View mRoot;
    private CardView mFront;
    private CardView mBack;
    private ImageView mSetting;
    private TextView mSeeWork;
    private TextView mClose;
    private Context mContext;

    private TextView mBookName;
    private TextView mBookNameBack;
    private ImageView mBookCover;
    private ImageView mBookCoverBack;

    private TextView mBookType;
    private TextView mCreateTime;
    private TextView mBookBrief;
    private TextView mAuthor;

    private TextView mJoinNum;
    private TextView mWriteNum;
    private TextView mLookNum;
    private TextView mGood;

    private static final String TAG = "MyWorkHolder";


    public MyWorkHolder(Context context, View itemView) {
        super(itemView);
        mRoot = itemView;
        mContext = context;
        mFront = mRoot.findViewById(R.id.card_front);
        mBack = mRoot.findViewById(R.id.card_back);
        mSetting = mRoot.findViewById(R.id.iv_setting);
        mSetting.setOnClickListener(this);
        mClose = mRoot.findViewById(R.id.tv_close);
        mClose.setOnClickListener(this);
        mSeeWork = mRoot.findViewById(R.id.tv_see_work);
        mSeeWork.setOnClickListener(this);

        mBookName = mRoot.findViewById(R.id.tv_novel_name);
        mBookNameBack = mRoot.findViewById(R.id.tv_novel_name_back);

        mBookCover = mRoot.findViewById(R.id.iv_novel_cover);
        mBookCoverBack = mRoot.findViewById(R.id.iv_novel_cover_back);
        mBookBrief = mRoot.findViewById(R.id.tv_novel_brief);
        mBookType = mRoot.findViewById(R.id.tv_novel_type);
        mCreateTime = mRoot.findViewById(R.id.tv_publish_time);
        mJoinNum = mRoot.findViewById(R.id.tv_join_number);
        mWriteNum = mRoot.findViewById(R.id.tv_write_number);
        mLookNum = mRoot.findViewById(R.id.tv_lookthrough_number);
        setCameraDistance();
    }

    public void setData(MyPublishModule data){

        mBookName.setText(data.getBookName());
        mBookNameBack.setText(data.getBookName());
        Glide.with(mContext)
                .load(data.getBookCover())
                .into(mBookCover);

        Glide.with(mContext)
                .load(data.getBookCover())
                .into(mBookCoverBack);
        mBookBrief.setText(data.getContent().subSequence(0,data.getContent().length()>9?
                9:data.getContent().length()-1)+"...");
        mBookType.setText(data.getBookType());
        mCreateTime.setText(data.getCreateTime());
        mWriteNum.setText(data.getBranchNum());
        mLookNum.setText(data.getReadNum());
        mJoinNum.setText(data.getJoinUsers());
        mAuthor.setText(data.getAuthor().getUserName());

    }

    @Override
    public void onClick(View v) {
       if(v.getId() == R.id.iv_setting) {

           openBack();
       }else  if(v.getId() == R.id.tv_close) {


           openFront();
       }else if(v.getId() == R.id.tv_see_work){

                mContext.startActivity(new Intent(mContext, NovelCaptureActivity.class));

        }
    }

    private void openBack(){
        ObjectAnimator front = (ObjectAnimator) AnimatorInflater.
                loadAnimator(mContext, R.animator.simple_animtor_card_front_gone);
        ObjectAnimator back = (ObjectAnimator)AnimatorInflater.
                loadAnimator(mContext,R.animator.simple_animtor_card_back_show);
       front.setTarget(mFront);
       back.setTarget(mBack);
       front.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
           @Override
           public void onAnimationUpdate(ValueAnimator animation) {
               float value = (float)animation.getAnimatedValue();
               if(value >= 90){
                   mFront.setVisibility(View.GONE);
                   mBack.setVisibility(View.VISIBLE);
               }
           }
       });
        back.start();
       front.start();

    }

    private void openFront(){

        ObjectAnimator front = (ObjectAnimator) AnimatorInflater.
                loadAnimator(mContext,R.animator.simple_animtor_card_front_show);
        ObjectAnimator back = (ObjectAnimator)AnimatorInflater.
                loadAnimator(mContext,R.animator.simple_animtor_card_back_gone);
        front.setTarget(mFront);
        back.setTarget(mBack);
        back.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float)animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: "+value);
                if(value <= -90){
                    mFront.setVisibility(View.VISIBLE);
                    mBack.setVisibility(View.GONE);
                }
            }
        });
        front.start();
        back.start();

    }

    private void setCameraDistance() {
        int distance = 16000;
        float scale = mContext.getResources().getDisplayMetrics().density * distance;
        mFront.setCameraDistance(scale);
        mBack.setCameraDistance(scale);
    }

}
