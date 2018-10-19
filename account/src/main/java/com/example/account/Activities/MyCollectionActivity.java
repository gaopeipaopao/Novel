package com.example.account.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.account.Adapters.MyCollectionAdapter;
import com.example.account.Models.MyCollectionModel;
import com.example.account.R;
import com.example.basecomponent.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends BaseActivity {

    private RecyclerView mRecyclerVIew;
    private ImageView mBack;
    private MyCollectionAdapter mAdapter;
    private List<MyCollectionModel> mDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity_my_collection);
        init();
    }

    private void init(){
        mRecyclerVIew = (RecyclerView)findViewById(R.id.recyclerview);
        mBack = (ImageView)findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter = new MyCollectionAdapter(this,mDatas);
        mRecyclerVIew.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerVIew.setAdapter(mAdapter);
    }


}
