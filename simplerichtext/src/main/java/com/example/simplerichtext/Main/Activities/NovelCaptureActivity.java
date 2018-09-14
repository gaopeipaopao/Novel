package com.example.simplerichtext.Main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basecomponent.Excutes.AddExcute;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.Main.Adapters.CaptureViewPagerAdater;
import com.example.simplerichtext.Main.Fragments.DraftFragment;
import com.example.simplerichtext.Main.Fragments.PublishedCaptureFragment;
import com.example.simplerichtext.Main.Fragments.RecycleBinFragment;
import com.example.simplerichtext.R;
import com.example.simplerichtext.RichTextView.RichTextActivity;

import java.util.ArrayList;
import java.util.List;

public class NovelCaptureActivity extends BaseActivity implements
        View.OnClickListener,ViewPager.OnPageChangeListener,DraftFragment.AddCallBackInterface{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();
    private CaptureViewPagerAdater mAdater;
    private String[] mTitles;
    private ImageView mBack;
    private TextView mBookName;
    private ImageView mAdd;
    private boolean mCanAdd = true;
    private String mStatus;
    private String mName;
    private String mCaptureBrief = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_novel_capture);
        Intent intent = getIntent();
        mName=(intent.getStringExtra("name"));
        mStatus = intent.getStringExtra("status");
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
        mBookName = findViewById(R.id.tv_novel_name);
        mBookName.setText(mName);
        mAdd = findViewById(R.id.iv_add);
        mAdd.setOnClickListener(this);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewpager);
        mFragments.add(new DraftFragment());
        mFragments.add(new PublishedCaptureFragment());
        mFragments.add(new RecycleBinFragment());
        mTitles = new String[]{
                getResources().getString(R.string.simple_draft_box),
                getResources().getString(R.string.simple_published_capture),
                getResources().getString(R.string.simple_recycle_bin)
        };

        mAdater = new CaptureViewPagerAdater(getSupportFragmentManager(),
                mFragments,mTitles);
        mViewPager.setAdapter(mAdater);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.iv_add){
            Intent intent = new Intent(this, RichTextActivity.class);
            intent.putExtra("capture","1");
            startActivity(intent);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(mStatus.equals( AddExcute.UNPUBLISHED)){
            if(position == 0&&mCanAdd){
                mAdd.setVisibility(View.VISIBLE);
            }else {
                mAdd.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void setAdd(boolean canAdd) {
        mCanAdd = canAdd;
        if(mCanAdd){
            mAdd.setVisibility(View.VISIBLE);
        }else {
            mAdd.setVisibility(View.GONE);
        }
    }
}
