package com.example.account.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.example.account.Adapters.AccountManageAdapter;
import com.example.account.Models.AccountModel;
import com.example.account.R;
import com.example.basecomponent.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AccountManageActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private ImageView mBack;
    private AccountManageAdapter mAdapter;
    private List<AccountModel> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity_account_manage);
        mDatas.add(new AccountModel());
        init();
    }

    private void init(){
        mBack = (ImageView)findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mAdapter = new AccountManageAdapter(this,mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
