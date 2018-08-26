package com.example.gaope.novel.Main.BookShelves;

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

import com.example.gaope.novel.Adapter.ViewpagerAdapter;
import com.example.gaope.novel.Base.BaseFragment;
import com.example.gaope.novel.Main.BookShelves.Collect.BookCollectFragment;
import com.example.gaope.novel.Main.BookShelves.Join.BookJoinFragment;
import com.example.gaope.novel.Main.BookShelves.Launch.BookLaunchFragment;
import com.example.gaope.novel.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class BookShelvesFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "BookShelvesFragment";

    private Context context;
    private TextView startBook;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewpagerAdapter findViewpagerAdapter;
    private List<Fragment> fragmentList;
    private String[] stringsTitles;
    private BookJoinFragment bookJoinFragment;

    public BookShelvesFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book,container,false);
        startBook = (TextView) view.findViewById(R.id.start_book);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_bottom);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_top);
        bookJoinFragment = new BookJoinFragment(context);
        init();
        return view;
    }

    private void init() {

        Log.d("hhhhhaaa","hahhhahahahahah");
        stringsTitles = new String[]{"我的收藏","我的发起","我的参与"};
        fragmentList = new ArrayList<>();
        Log.d("tatta","hauau");
        fragmentList.add(new BookCollectFragment(context));
        fragmentList.add(new BookLaunchFragment(context));
        fragmentList.add(bookJoinFragment);
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //打开发起新书的活动
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
