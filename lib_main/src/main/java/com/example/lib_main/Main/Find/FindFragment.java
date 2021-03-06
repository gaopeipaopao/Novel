package com.example.lib_main.Main.Find;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.lib_main.Adapter.ViewpagerAdapter;
import com.example.lib_main.Base.BaseFragment;
import com.example.lib_main.Main.Find.Position.FindPositionFragment;
import com.example.lib_main.Main.Find.Recent.FindRecentFragment;
import com.example.lib_main.Main.Find.Recommend.FindRecommendFragment;
import com.example.lib_main.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class FindFragment extends BaseFragment {

    private static final String TAG = "FindFragment";

    //Fragment所依赖的Activity
    private Context context;
    private TextView startBook;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewpagerAdapter findViewpagerAdapter;
    private List<Fragment> fragmentList;
    private String[] stringsTitles;


     public FindFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find,container,false);
        startBook = (TextView) view.findViewById(R.id.start_book);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_bottom);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_top);
        //context = getActivity();
        Log.d(TAG,":"+context);

        init();
        return view;
    }

    private void init() {
         stringsTitles = new String[]{"今日推荐","排行榜","最新"};
         fragmentList = new ArrayList<>();
         fragmentList.add(new FindRecommendFragment(context));
         fragmentList.add(new FindPositionFragment(context));
         fragmentList.add(new FindRecentFragment(context));
         findViewpagerAdapter = new ViewpagerAdapter(getChildFragmentManager(),fragmentList,stringsTitles);
         viewPager.setAdapter(findViewpagerAdapter);

     //    tabLayout.setTabMode(TabLayout.MODE_FIXED);//TabLayout.MODE_FIXED为tabLayout的模式
      //   tabLayout.setSelectedTabIndicatorHeight(0);//高度为0，就隐藏了indication
     //    ViewCompat.setElevation(tabLayout, 10);//设置tab之间的间距
         for (int i = 0; i < 3; i++) {
             TabLayout.Tab tab = tabLayout.newTab();
             tab.setText(stringsTitles[i]);
            tabLayout.addTab(tab);
         }
         tabLayout.setupWithViewPager(viewPager);
         tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
         viewPager.setCurrentItem(0);
         viewPager.setOffscreenPageLimit(3);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startBook.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG,"onDestory");
    }

}
