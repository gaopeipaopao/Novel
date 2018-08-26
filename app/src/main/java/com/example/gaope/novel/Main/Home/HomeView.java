package com.example.gaope.novel.Main.Home;

import com.example.gaope.novel.Base.BaseView;

public interface HomeView extends BaseView {

    //获得新的access_token
    void sendToken();

    //提醒用户网络没有连接
    void showNoNetwork();

    //将access_token和refresh_token设置为新得到的
    void setToken(String accessToken,String refreshToken);
}
