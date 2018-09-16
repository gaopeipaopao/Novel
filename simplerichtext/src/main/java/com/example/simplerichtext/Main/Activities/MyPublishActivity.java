package com.example.simplerichtext.Main.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
                import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basecomponent.Excutes.AddExcute;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.PermissionUtil;
import com.example.basecomponent.Util;
import com.example.simplerichtext.Add.AddActivity;
import com.example.simplerichtext.Add.AddBookMessage;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.Main.Adapters.MyWorkAdapter;
import com.example.simplerichtext.Main.Adapters.PublishViewPagerAdapter;
import com.example.simplerichtext.Main.Fragments.PublishedFragment;
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
public class MyPublishActivity extends BaseActivity implements
        View.OnClickListener,ViewPager.OnPageChangeListener {

    private ImageView mBack;

    private TextView mAddWork;
    private MyWorkHolder mRefreshHolder;
    private String mPath;
    public static final int STORAGE_CODE = 200;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Fragment mPublishedFragment;
    private Fragment mNoPublishedFragment;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();
    private PublishViewPagerAdapter mAdapter;

    private static final String TAG = "MyPublishActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_mypulish);
        EventBus.getDefault().register(this);
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
        mAddWork = findViewById(R.id.tv_add);
        mAddWork.setOnClickListener(this);
        mPublishedFragment = new PublishedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("status", HttpUtil.STATUS_PUBLISHED);
        mPublishedFragment.setArguments(bundle);
        mNoPublishedFragment = new PublishedFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("status", HttpUtil.STATUS_UNPUBLISHED);
        mNoPublishedFragment.setArguments(bundle1);
        mFragments.add(mPublishedFragment);
        mFragments.add(mNoPublishedFragment);
        mNames.add(getResources().getString(R.string.simple_published));
        mNames.add(getResources().getString(R.string.simple_no_published));
        mViewPager = findViewById(R.id.viewpager);
        mAdapter = new PublishViewPagerAdapter(getSupportFragmentManager(),
                mFragments,mNames);
        mViewPager.setAdapter(mAdapter);
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);
        if(!EasyPermissions.hasPermissions(this, PermissionUtil.STORAGES)) {
            PermissionUtil.
                    requestStoragePersmission(this,
                            STORAGE_CODE);
        }

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_add){

                startActivity(new Intent(MyPublishActivity.this,
                        AddActivity.class));
        }
    }



    private void showLoading(){

    }


    public void refreshImage(MyWorkHolder holder){
        mRefreshHolder = holder;

    }

    @Subscribe
    public void updateData(AddBookMessage message){
        if(message!=null){

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MyWorkHolder.PHOTO_CODE&&resultCode == RESULT_OK){
            List<Uri> images = Matisse.obtainResult(data);
            if(mRefreshHolder!=null){
                Uri uri = images.get(0);
               mPath = Util.handleImage(this,uri);


            }
        }

        if(requestCode == MyWorkHolder.UPDATE_NAME &&resultCode == RESULT_OK){
            MyPublishModule myPublishModule = (MyPublishModule) data.
                    getBundleExtra("book").getSerializable("book");
            if(mRefreshHolder!=null){
                //mDatas.set((int) mRefreshHolder.getItemId(),myPublishModule);
                mRefreshHolder.setData(myPublishModule);
                Log.d(TAG, "onActivityResult: "
                        +myPublishModule.getBookName());
                //mAdapter.notifyItemChanged((int) mRefreshHolder.getItemId());

            }
        }

        if(requestCode == MyWorkHolder.UPDATE_BRIEF&&resultCode == RESULT_OK){
            MyPublishModule myPublishModule = (MyPublishModule) data.
                    getBundleExtra("book").getSerializable("book");
            if(mRefreshHolder!=null){
                //mDatas.set((int) mRefreshHolder.getItemId(),myPublishModule);
                mRefreshHolder.setData(myPublishModule);
                Log.d(TAG, "onActivityResult: "
                        +myPublishModule.getBookName());
                //mAdapter.notifyItemChanged((int) mRefreshHolder.getItemId());

            }
        }

        if(requestCode == MyWorkHolder.UPDATE_TYPE&&resultCode == RESULT_OK){
            MyPublishModule myPublishModule = (MyPublishModule) data.
                    getBundleExtra("book").getSerializable("book");
            if(mRefreshHolder!=null){
                //mDatas.set((int) mRefreshHolder.getItemId(),myPublishModule);
                mRefreshHolder.setData(myPublishModule);
                Log.d(TAG, "onActivityResult: "
                        +myPublishModule.getBookName());
                //mAdapter.notifyItemChanged((int) mRefreshHolder.getItemId());

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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0){
            mAddWork.setVisibility(View.INVISIBLE);
        }else {
            mAddWork.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
