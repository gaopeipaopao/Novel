package com.example.simplerichtext.Main.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Add.AddBookMessage;
import com.example.simplerichtext.Main.Adapters.MyWorkAdapter;
import com.example.simplerichtext.Main.Holders.MyWorkHolder;
import com.example.simplerichtext.Main.Presenters.MyPublishPresenter;
import com.example.simplerichtext.R;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class PublishedFragment extends Fragment implements
        MyPublishPresenter.myPublishViewLisnter{

    private RecyclerView mRecyclerView;
    private MyWorkAdapter mAdapter;
    private MyPublishPresenter mPresenter;
    private List<MyPublishModule> mDatas = new ArrayList<>();
    private MyWorkHolder mRefreshHolder;
    private String mPath;
    public static final int STORAGE_CODE = 200;
    private PagerSnapHelper mPagerSnapHelper;
    private  String mStatus;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate
                (R.layout.simple_fragment_publish,container,false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new MyWorkAdapter(getActivity(),mDatas);
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
        if(mRefreshHolder!=null){
            MyPublishModule myPublishModule = mRefreshHolder.getData();
            myPublishModule.setBookCover(mPath);
            mRefreshHolder.setData(myPublishModule);
        }
    }

    @Override
    public void uploadImageFailed() {

        Toast.makeText(getContext(),getResources().getText(R.string.simple_upload_failed)
                ,Toast.LENGTH_SHORT).show();
    }


}
