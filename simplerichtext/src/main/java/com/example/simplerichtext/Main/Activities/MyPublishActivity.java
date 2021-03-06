package com.example.simplerichtext.Main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.PermissionUtil;
import com.example.simplerichtext.Add.AddActivity;
import com.example.simplerichtext.Add.AddBookMessage;
import com.example.basecomponent.BaseActivity;
import com.example.simplerichtext.Main.Adapters.PublishViewPagerAdapter;
import com.example.simplerichtext.Main.Fragments.PublishedFragment;
import com.example.simplerichtext.Main.Holders.MyWorkHolder;
import com.example.simplerichtext.R;

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
    private PublishedFragment mNoPublishedFragment;
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
        mAddWork.setVisibility(View.INVISIBLE);
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






    @Subscribe
    public void updateData(AddBookMessage message){
        if(message!=null){
            mNoPublishedFragment.AddData(message.getModule());
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_CODE){
            if(EasyPermissions.hasPermissions(this,
                    PermissionUtil.STORAGES)){
//                Matisse.from(this)
//                        .choose(MimeType.of(MimeType.valueOf("image")))
//                        .countable(true)
//                        .maxSelectable(1)
//                        .gridExpectedSize(getResources().
//                                getDimensionPixelSize(R.dimen.simple_book_cover_expected_size))
//                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                        .thumbnailScale(0.85f) // 缩略图的比例
//                        .imageEngine(new GlideEngine()) // 使用的图片加载引擎
//                        .forResult(MyWorkHolder.PHOTO_CODE); // 设置作为标记的请求码
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
