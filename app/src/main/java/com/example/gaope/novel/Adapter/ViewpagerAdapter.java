package com.example.gaope.novel.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现和书架和消息的共同的Viewpager的适配器
 */

public class ViewpagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentsList;
    private String[] titles;

    public ViewpagerAdapter(FragmentManager fm, List<Fragment> fragmentsList, String[] titles) {
        super(fm);
        Log.d("pager","pager");
        this.fragmentsList = fragmentsList;
        Log.d("pagerlist","pagerlist:"+fragmentsList.size());
        Log.d("pagerFragment",":"+fragmentsList.get(1));
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("hahhahjefjird","viewpager:"+fragmentsList.get(position));
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
