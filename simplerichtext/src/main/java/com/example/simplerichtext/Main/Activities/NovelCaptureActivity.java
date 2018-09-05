package com.example.simplerichtext.Main.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.Main.Adapters.CaptureViewPagerAdater;
import com.example.simplerichtext.Main.Fragments.DraftFragment;
import com.example.simplerichtext.Main.Fragments.PublishedCaptureFragment;
import com.example.simplerichtext.Main.Fragments.RecycleBinFragment;
import com.example.simplerichtext.R;

import java.util.ArrayList;
import java.util.List;

public class NovelCaptureActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();
    private CaptureViewPagerAdater mAdater;
    private String[] mTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_novel_capture);
        init();
    }

    private void init(){
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

    }
}
