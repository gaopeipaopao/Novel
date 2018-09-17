package com.example.simplerichtext.Main.Holders;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.basecomponent.Excutes.AddExcute;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.PermissionUtil;
import com.example.basecomponent.Util;
import com.example.simplerichtext.Add.BookBriefActivity;
import com.example.simplerichtext.Add.BookTypeActivity;
import com.example.simplerichtext.Main.Activities.EDBookNameActivity;
import com.example.simplerichtext.Main.Activities.MyPublishActivity;
import com.example.simplerichtext.Main.Activities.NovelCaptureActivity;
import com.example.simplerichtext.Main.Fragments.PublishedFragment;
import com.example.simplerichtext.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import pub.devrel.easypermissions.EasyPermissions;

public class MyWorkHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private View mRoot;
    private CardView mFront;
    private CardView mBack;
    private ImageView mSetting;
    private TextView mSeeWork;
    private TextView mClose;
    private Fragment mContext;

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

    private MyPublishModule mData;

    private static final String TAG = "MyWorkHolder";
    public static final int UPDATE_NAME = 500;
    public static final int UPDATE_BRIEF = 600;
    public static final int UPDATE_TYPE = 700;
    public static final int PHOTO_CODE = 200;


    public MyWorkHolder(Fragment context, View itemView) {
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
        mBookType = mRoot.findViewById(R.id.tv_novel_type);
        mCreateTime = mRoot.findViewById(R.id.tv_publish_time);
        mJoinNum = mRoot.findViewById(R.id.tv_join_number);
        mWriteNum = mRoot.findViewById(R.id.tv_write_number);
        mLookNum = mRoot.findViewById(R.id.tv_lookthrough_number);
        mBookBrief = mRoot.findViewById(R.id.tv_novel_brief);

        mBookNameBack.setOnClickListener(this);
        mBookCoverBack.setOnClickListener(this);
        mBookBrief.setOnClickListener(this);
        mBookType.setOnClickListener(this);


        setCameraDistance();
    }

    public void setData(MyPublishModule data){
        mData = data;
        Log.d(TAG, "setData: "+data.getBookName());
        mBookName.setText(data.getBookName());
        mBookNameBack.setText(data.getBookName());
        if(data.getBookCover() == null ||data.getBookCover().equals("")){
            Glide.with(mContext)
                    .load(R.mipmap.simple_book_cover)
                    .into(mBookCover);

            Glide.with(mContext)
                    .load(R.mipmap.simple_book_cover)
                    .into(mBookCoverBack);
        }else {
            Glide.with(mContext)
                    .load(HttpUtil.BOOK_COVER+data.getBookCover())

                    .into(mBookCover);

            Glide.with(mContext)
                    .load(HttpUtil.BOOK_COVER+data.getBookCover())
                    .into(mBookCoverBack);
        }

        mBookBrief.setText(data.getContent().subSequence(0,data.getContent().length()>9?
                9:data.getContent().length()-1)+"...");
        mBookType.setText(Util.getTypeValue(data.getBookType()));
        mCreateTime.setText(data.getCreateTime());
        mLookNum.setText(String.valueOf(data.getReadNum()));
        mJoinNum.setText(String.valueOf(data.getJoinUsers()));
        if(data.getAuthor()!=null){
           // mAuthor.setText(data.getAuthor().getUserName());
        }

        mWriteNum.setText(String.valueOf(data.getBranchNum()));

    }

    public void updateImage(String path){
        mData.setBookCover(path);
        Glide.with(mContext)
                .load(HttpUtil.BOOK_COVER+path)

                .into(mBookCover);

        Glide.with(mContext)
                .load(HttpUtil.BOOK_COVER+path)
                .into(mBookCoverBack);

    }

    public MyPublishModule getData(){
        return mData;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.iv_setting) {

            openBack();
        }else  if(v.getId() == R.id.tv_close) {


            openFront();
        }else if(v.getId() == R.id.tv_see_work){

            Intent intent = new Intent(mContext.getContext(),
                    NovelCaptureActivity.class);
            intent.putExtra("name",mData.getBookName());
            intent.putExtra("status", AddExcute.UNPUBLISHED);
            mContext.startActivity(intent);

        }else if(mData.getStatus().equals(HttpUtil.STATUS_UNPUBLISHED)){
            if(v.getId() == R.id.iv_novel_cover_back){
                ((PublishedFragment)mContext).setFreshHolder(this);
                if(!EasyPermissions.hasPermissions(mContext.getContext(),
                        PermissionUtil.STORAGES)) {
                    PermissionUtil.
                            requestStoragePersmission(mContext.getActivity(),
                                    MyPublishActivity.STORAGE_CODE);
                }else {
                    Matisse.from(mContext)
                            .choose(MimeType.allOf())
                            .countable(true)
                            .maxSelectable(1)
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .spanCount(3)
                            .thumbnailScale(0.85f) // 缩略图的比例
                            .theme(R.style.Matisse_Dracula)
                            .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                            .forResult(PHOTO_CODE); // 设置作为标记的请求码

                }

                ((PublishedFragment)mContext).setFreshHolder(this);

            }else if(v.getId() == R.id.tv_novel_name_back){
                ((PublishedFragment)mContext).setFreshHolder(MyWorkHolder.this);

                Intent intent = new Intent(mContext.getContext(),
                        EDBookNameActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("book",mData);
                intent.putExtra("book",bundle);
                mContext.startActivityForResult(intent,UPDATE_NAME);
            }else if(v.getId() == R.id.tv_novel_brief){
                ((PublishedFragment)mContext).setFreshHolder(this);
                Intent intent = new Intent(mContext.getContext(),
                        BookBriefActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("book",mData);
                bundle.putBoolean("update",true);
                intent.putExtra("book",bundle);
                mContext.startActivityForResult(intent,UPDATE_BRIEF);

            }else if(v.getId() == R.id.tv_novel_type){
                ((PublishedFragment)mContext).setFreshHolder(this);
                Intent intent = new Intent(mContext.getContext(),
                        BookTypeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("book",mData);
                bundle.putBoolean("update",true);
                intent.putExtra("book",bundle);
                mContext.startActivityForResult(intent,UPDATE_TYPE);

            }
        }

    }

    private void openBack(){
        ObjectAnimator front = (ObjectAnimator) AnimatorInflater.
                loadAnimator(mContext.getContext(),
                        R.animator.simple_animtor_card_front_gone);
        ObjectAnimator back = (ObjectAnimator)AnimatorInflater.
                loadAnimator(mContext.getContext(),
                        R.animator.simple_animtor_card_back_show);
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
                loadAnimator(mContext.getContext(),R.animator.simple_animtor_card_front_show);
        ObjectAnimator back = (ObjectAnimator)AnimatorInflater.
                loadAnimator(mContext.getContext(),R.animator.simple_animtor_card_back_gone);
        front.setTarget(mFront);
        back.setTarget(mBack);
        back.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float)animation.getAnimatedValue();
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
