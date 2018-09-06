package com.example.lib_main.Entry;

public interface IEntryPresenter {

    //登录发送的请求
    void sendEntry(String stringAccount, String stringPassword, String stringNumber, String imei);

    //得到图片验证码发送的请求
    void imageNumber(String imei);

}
