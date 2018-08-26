package com.example.gaope.novel.Main.News;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.gaope.novel.Adapter.ViewpagerAdapter;
import com.example.gaope.novel.Main.BookShelves.Collect.BookCollectFragment;
import com.example.gaope.novel.Main.BookShelves.Join.BookJoinFragment;
import com.example.gaope.novel.Main.BookShelves.Launch.BookLaunchFragment;
import com.example.gaope.novel.Main.News.Chat.NewsChatFragment;
import com.example.gaope.novel.Main.News.Notify.NewsNotifyFragment;
import com.example.gaope.novel.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class NewsFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "NewsFragment";

    private Context context;
    private ImageView newsAdd;
    private ImageView newsMessage;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewpagerAdapter findViewpagerAdapter;
    private List<Fragment> fragmentList;
    private String[] stringsTitles;

    public NewsFragment(Context context){
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        newsAdd = (ImageView) view.findViewById(R.id.news_add);
        newsMessage = (ImageView) view.findViewById(R.id.news_message);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_news);
        init();
        return view;
    }

    private void init() {

        stringsTitles = new String[]{"通知","聊天"};
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsChatFragment());
        fragmentList.add(new NewsNotifyFragment());
        findViewpagerAdapter = new ViewpagerAdapter(getFragmentManager(),fragmentList,stringsTitles);
        viewPager.setAdapter(findViewpagerAdapter);

        //    tabLayout.setTabMode(TabLayout.MODE_FIXED);//TabLayout.MODE_FIXED为tabLayout的模式
        //   tabLayout.setSelectedTabIndicatorHeight(0);//高度为0，就隐藏了indication
        //    ViewCompat.setElevation(tabLayout, 10);//设置tab之间的间距
        for (int i = 0; i < 2; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(stringsTitles[i]);
            tabLayout.addTab(tab);
        }
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        newsAdd.setOnClickListener(this);
        newsMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_add:
                break;
            case R.id.news_message:
                break;
        }
    }
}
