package com.example.simplerichtext.Main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.simplerichtext.Add.AddActivity;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.Main.Adapters.MyWorkAdapter;
import com.example.simplerichtext.Main.Presenters.MyPublishPresenter;
import com.example.simplerichtext.R;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/simple/myPublishActivity")
public class MyPublishActivity extends BaseActivity implements View.OnClickListener
        ,MyPublishPresenter.myPublishViewLisnter{

    private ImageView mBack;
    private RecyclerView mRecyclerView;
    private TextView mAddWork;
    private MyWorkAdapter mAdapter;
    private MyPublishPresenter  mPresenter;
    private List<MyPublishModule> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_mypulish);
        mPresenter = new MyPublishPresenter(this);
        init();
        getData();
    }

    private void init(){
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new MyWorkAdapter(this,mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(mAdapter);
        mAddWork = findViewById(R.id.tv_add);
        mAddWork.setOnClickListener(this);
    }

    private void getData(){

        mPresenter.getData();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_add){

                startActivity(new Intent(MyPublishActivity.this,
                        AddActivity.class));
        }
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
    protected void onDestroy() {
        mPresenter.dettachView();
        super.onDestroy();
    }
}
