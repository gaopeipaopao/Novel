package com.example.lib_main.Main.Home;

public interface IHomePresenter {

    //调用Modle发送请求获得新的access_token
    void sendToken(String refreshToken);
}
