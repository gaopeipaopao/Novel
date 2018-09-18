package com.example.simplerichtext.Main.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Util;
import com.example.simplerichtext.Add.AddBookMessage;
import com.example.simplerichtext.Main.Adapters.MyWorkAdapter;
import com.example.simplerichtext.Main.Holders.MyWorkHolder;
import com.example.simplerichtext.Main.Presenters.MyPublishPresenter;
import com.example.simplerichtext.Main.PublishBookMessage;
import com.example.simplerichtext.R;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PublishedFragment extends Fragment implements
        MyPublishPresenter.myPublishViewLisnter{

    private RecyclerView mRecyclerView;
    private MyWorkAdapter mAdapter;
    private MyPublishPresenter mPresenter;
    private List<MyPublishModule> mDatas = new ArrayList<>();
    private String mPath;
    public static final int STORAGE_CODE = 200;
    private PagerSnapHelper mPagerSnapHelper;
    private  String mStatus;
    private MyWorkHolder mFreshHodler;
    private static final String TAG = "PublishedFragment";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate
                (R.layout.simple_fragment_publish,container,false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new MyWorkAdapter(this,mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(mAdapter);
        mPagerSnapHelper = new PagerSnapHelper();
        mPagerSnapHelper.attachToRecyclerView(mRecyclerView);
        mPresenter = new MyPublishPresenter(this);
        Bundle bundle = getArguments();
        mStatus = bundle.getString("status");
        getData();
        return view;
    }

    private void getData(){

        mPresenter.getData(mStatus);
    }

    @Override
    public void setMyPublishData(List<MyPublishModule> myPublishModuleList) {
        mDatas.clear();
        mDatas.addAll(myPublishModuleList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setDataError() {

    }

    @Override
    public void uploadImageSucssed() {
        if(mFreshHodler!=null){
            MyPublishModule myPublishModule = mFreshHodler.getData();
            myPublishModule.setBookCover(mPath);
            mFreshHodler.setData(myPublishModule);
        }
    }

    @Override
    public void uploadImageFailed() {

        Toast.makeText(getContext(),getResources().getText(R.string.simple_upload_failed)
                ,Toast.LENGTH_SHORT).show();
    }

    public void setFreshHolder(MyWorkHolder holder){
        mFreshHodler = holder;
    }


    private void updateHodlerImage(String path){
        if(mFreshHodler!=null){

        }
    }

    private void updateHolder(MyPublishModule module){
        if(mFreshHodler!= null){
            mFreshHodler.setData(module);
        }
    }

    public void AddData(MyPublishModule module){
        mDatas.add(module);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode,resultCode,data);
        Log.d(TAG, "onActivityResult: ");
        if(requestCode == MyWorkHolder.PHOTO_CODE&&resultCode ==
                Activity.RESULT_OK){
            List<Uri> images = Matisse.obtainResult(data);
            if(mFreshHodler!=null){
                Uri uri = images.get(0);
                mPath = Util.handleImage(getContext(),uri);

            }
        }

        if(requestCode == MyWorkHolder.UPDATE_NAME &&resultCode ==
                Activity.RESULT_OK){
            MyPublishModule myPublishModule = (MyPublishModule) data.
                    getBundleExtra("book").getSerializable("book");
            updateHolder(myPublishModule);
        }

        if(requestCode == MyWorkHolder.UPDATE_BRIEF&&
                resultCode == Activity.RESULT_OK){
            MyPublishModule myPublishModule = (MyPublishModule) data.
                    getBundleExtra("book").getSerializable("book");
                updateHolder(myPublishModule);

            }

        if(requestCode == MyWorkHolder.UPDATE_TYPE&&
                resultCode == Activity.RESULT_OK){
            MyPublishModule myPublishModule = (MyPublishModule) data.
                    getBundleExtra("book").getSerializable("book");
                //mDatas.set((int) mRefreshHolder.getItemId(),myPublishModule);
               updateHolder(myPublishModule);


        }

        if(requestCode == MyWorkHolder.PUBLISH_BOOK&&
                resultCode == Activity.RESULT_OK){
            //getData();
            EventBus.getDefault().post(new PublishBookMessage());
        }
    }

    @Subscribe
    public void publishBook(PublishBookMessage message){
        getData();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
