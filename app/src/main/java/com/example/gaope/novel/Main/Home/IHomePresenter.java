package com.example.gaope.novel.Main.Home;

import com.example.gaope.novel.Base.BasePresenter;

public interface IHomePresenter {

    //调用Modle发送请求获得新的access_token
    void sendToken(String refreshToken);
}
