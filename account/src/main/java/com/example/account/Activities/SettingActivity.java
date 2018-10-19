package com.example.account.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.account.R;
import com.example.basecomponent.BaseActivity;

@Route(path = "/account/SettingActivity")
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private TextView mMyInfo;
    private TextView mAccountManage;
    private TextView mAccountSafe;
    private ImageView mDark;
    private TextView mQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity_setting);
        init();
    }

    private void init(){
        mBack = (ImageView)findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);

        mAccountManage = (TextView)findViewById(R.id.tv_account_manage);
        mAccountManage.setOnClickListener(this);

        mAccountSafe = (TextView)findViewById(R.id.tv_account_safe);
        mAccountSafe.setOnClickListener(this);

        mDark = (ImageView)findViewById(R.id.iv_dark);
        mDark.setOnClickListener(this);

        mQuit = (TextView)findViewById(R.id.tv_quit);
        mQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_back){
            finish();
        }else if(v.getId() == R.id.tv_my_info){

            Intent intent = new Intent(this,MyInfoActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.tv_account_manage){

            Intent intent = new Intent(this,AccountManageActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.tv_account_safe){
            Intent intent = new Intent(this,AccountSafeActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.iv_dark){

        }else if(v.getId()==R.id.tv_quit){
            System.exit(0);
        }
    }
}
