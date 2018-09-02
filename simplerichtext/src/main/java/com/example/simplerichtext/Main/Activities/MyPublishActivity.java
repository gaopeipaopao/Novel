package com.example.simplerichtext.Main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.simplerichtext.Add.AddActivity;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.Main.Adapters.MyWorkAdapter;
import com.example.simplerichtext.R;

public class MyPublishActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mBack;
    private RecyclerView mRecyclerView;
    private TextView mAddWork;
    private MyWorkAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypulish);
        init();
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
        mAdapter = new MyWorkAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false));
        mRecyclerView.setAdapter(mAdapter);
        mAddWork = findViewById(R.id.tv_add);
        mAddWork.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_add){

                startActivity(new Intent(MyPublishActivity.this,
                        AddActivity.class));
        }
    }
}
