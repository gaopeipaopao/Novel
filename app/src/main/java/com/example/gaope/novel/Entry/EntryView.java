package com.example.gaope.novel.Entry;

import android.graphics.Bitmap;

import com.example.gaope.novel.Base.BaseView;

import java.nio.Buffer;

public interface EntryView extends BaseView {

    //提醒用户网络没有连接
    void showNoNetwork();

    //设置验证码的图片为默认的图片
    void setDefaultImage();

    //提示用户先要获得验证码
    void showGetNumber();

    //将得到的验证码图片加载
    void laodImageNumber(Bitmap response);

    //提示用户名或者密码错误
    void showMistake();

    //提示用户验证码错误
    void showMistakeNumber();

    //登录成功
    void intentNewActivity(String accessToken,String refreshToken);

}
