package com.example.lib_main.Register;

import com.example.lib_main.Base.BaseView;

public interface RegisterView extends BaseView {

//    //提醒用户输入名称
//    void showAccount();
//
//    //提醒用户输入密码
//    void showPassword();
//
//    //提醒用户再次输入密码
//    void showPaawordAgain();

//    //提醒用户名称必须在2到20之间
//    void showAccountLength();

    //提醒密码称必须在6到30之间
    void showPasswordLength();

    //判断两次的密码是否相同
    void judgePassword();

    //注册成功，打开登录界面
    void intentEntry();

//    //注册的手机号已经存在
//    void registerFailPhone();

//    //注册的用户名已经存在
//    void registerFailAccount();

    //检测网络是否连接
    void showNoNetwork();

}
