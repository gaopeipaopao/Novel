package com.example.lib_main.Main.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.basecomponent.HttpUtil;
import com.example.lib_main.Base.BaseActivity;
import com.example.lib_main.Main.BookShelves.BookShelvesFragment;
import com.example.lib_main.Main.Find.FindFragment;
import com.example.lib_main.Main.Mine.MineFragment;
import com.example.lib_main.Main.Mine.MineInfor.MinePresenter;
import com.example.lib_main.Main.News.NewsFragment;
import com.example.lib_main.R;


/**
 * 发现，书架，消息，我的，的底部的Activity
 * 当access_token无效时，重新获取
 */

public class HomeActivity extends BaseActivity implements HomeView,BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "HomeActivity";

    private FrameLayout homeFragmnet;
    private BottomNavigationBar bottomNavigationBar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    public static String accessToken;
    public static String refreshToken;
    public static Handler handlers;
    private HomePresenter homePresenter;
    private Boolean first = false;
    private MineFragment mineFragment ;

    /**
     * 在HomeActivity中得到MinePresenter和MainFragment，并将MinePresenter和MainFragment之间建立关联
     */
    private MinePresenter minePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mineFragment = new MineFragment(this);
//        Log.d(TAG,"mainFragmentaaa:"+mineFragment.getContext());
        Intent intent = getIntent();
        accessToken = intent.getStringExtra("access_token");
        refreshToken = intent.getStringExtra("refresh_token");
    //    Log.d(TAG,"accessToke:"+accessToken);

        homePresenter = new HomePresenter(this);
        homePresenter.attachView(this);

        minePresenter = new MinePresenter(this);
    //    minePresenter.attachView(new MineFragment(this));

        homeFragmnet = (FrameLayout) findViewById(R.id.home_fragment);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.home_bootomnaviga);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.home_fragment,new FindFragment(this));
        fragmentTransaction.commit();
        init();

        if (!first){
        //    Log.d(TAG,"111");
            first = true;
            sendToken();
        }

        handlers = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
               //         Log.d(TAG,"666");
                        sendToken();
                        break;
                }
            }
        };
    }

    private void init() {

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setBarBackgroundColor(R.color.bg_color)
                .addItem(
                new BottomNavigationItem(R.mipmap.finds,"发现")
                .setActiveColorResource(R.color.main_bule)
                .setInActiveColorResource(R.color.Text_color))
                .addItem(new BottomNavigationItem(R.mipmap.book,"书架")
                .setInActiveColorResource(R.color.Text_color)
                .setActiveColorResource(R.color.main_bule))
                .addItem(new BottomNavigationItem(R.mipmap.newss,"消息")
                .setActiveColorResource(R.color.main_bule)
                .setInActiveColorResource(R.color.Text_color))
                .addItem(new BottomNavigationItem(R.mipmap.mine,"我的")
                .setInActiveColorResource(R.color.Text_color)
                .setActiveColorResource(R.color.main_bule))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.detachView();
    }

    @Override
    public void onTabSelected(int position) {
        fragmentTransaction = fragmentManager.beginTransaction();
        if (position != 3){
        //    Log.d(TAG,"detachMainPresenter");
            minePresenter.detachView();
        }
        switch (position){
            case 0:
                fragmentTransaction.replace(R.id.home_fragment,new FindFragment(this));
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.home_fragment,new BookShelvesFragment(this));
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.home_fragment,new NewsFragment(this));
                fragmentTransaction.commit();
                break;
            case 3:
                Log.d(TAG,"mainFragmentaaa:"+mineFragment.getContext());
                fragmentTransaction.replace(R.id.home_fragment,mineFragment);
                fragmentTransaction.commit();
                minePresenter.attachView(mineFragment);
                mineFragment.attachPresenter(minePresenter);
                break;
        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void sendToken() {
        Log.d(TAG,"homeAct:222");
        homePresenter.sendToken(refreshToken);
    }

    @Override
    public void showNoNetwork() {
        Log.d(TAG,"base");
        Toast.makeText(getContexts(),"当前网络不可用，请检查网络连接",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setToken(String accessToken, String refreshToken) {
        Log.d(TAG,"555");
        HttpUtil.setAccessToken(accessToken);
        HttpUtil.setRefreshToken(refreshToken);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
