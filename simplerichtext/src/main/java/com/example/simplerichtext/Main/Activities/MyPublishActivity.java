package com.example.simplerichtext.Main.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
                import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basecomponent.Excutes.AddExcute;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.PermissionUtil;
import com.example.basecomponent.Util;
import com.example.simplerichtext.Add.AddActivity;
import com.example.simplerichtext.Add.AddBookMessage;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.Main.Adapters.MyWorkAdapter;
import com.example.simplerichtext.Main.Holders.MyWorkHolder;
import com.example.simplerichtext.Main.Presenters.MyPublishPresenter;
import com.example.simplerichtext.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

@Route(path = "/simple/myPublishActivity")
public class MyPublishActivity extends BaseActivity implements View.OnClickListener
        ,MyPublishPresenter.myPublishViewLisnter{

    private ImageView mBack;
    private RecyclerView mRecyclerView;
    private TextView mAddWork;
    private MyWorkAdapter mAdapter;
    private MyPublishPresenter  mPresenter;
    private List<MyPublishModule> mDatas = new ArrayList<>();
    private MyWorkHolder mRefreshHolder;
    private String mPath;
    public static final int STORAGE_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_mypulish);
        mPresenter = new MyPublishPresenter(this);
        EventBus.getDefault().register(this);
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

        if(!EasyPermissions.hasPermissions(this, PermissionUtil.STORAGES)) {
            PermissionUtil.
                    requestStoragePersmission(this,
                            STORAGE_CODE);
        }

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
    public void uploadImageSucssed() {
        if(mRefreshHolder!=null){
            MyPublishModule myPublishModule = mRefreshHolder.getData();
            myPublishModule.setBookCover(mPath);
            mRefreshHolder.setData(myPublishModule);
        }
    }

    @Override
    public void uploadImageFailed() {

        Toast.makeText(this,getResources().getText(R.string.simple_upload_failed)
                ,Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void updateData(AddBookMessage message){
        if(message!=null){
//            mDatas.add(message.getModule());
//            mAdapter.notifyDataSetChanged();
            getData();
        }


    }

    private void showLoading(){

    }



    public void refreshImage(MyWorkHolder holder){
        mRefreshHolder = holder;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MyWorkHolder.PHOTO_CODE&&resultCode == RESULT_OK){
            List<Uri> images = Matisse.obtainResult(data);
            if(mRefreshHolder!=null){
                Uri uri = images.get(0);
               mPath = Util.handleImage(this,uri);
                mPresenter.uploadImage(mRefreshHolder.getData().getBookId(),mPath);

            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_CODE){
            if(EasyPermissions.hasPermissions(this,
                    PermissionUtil.STORAGES)&&mRefreshHolder!=null                            ){
                Matisse.from(this)
                        .choose(MimeType.of(MimeType.valueOf("image")))
                        .countable(true)
                        .maxSelectable(1)
                        .gridExpectedSize(getResources().
                                getDimensionPixelSize(R.dimen.simple_book_cover_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f) // 缩略图的比例
                        .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                        .forResult(MyWorkHolder.PHOTO_CODE); // 设置作为标记的请求码
            }else {
                Toast.makeText(this,R.string.simple_permission_deined,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.dettachView();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
