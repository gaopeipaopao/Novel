package com.example.gaope.novel.Prove;

public interface IProvePresenter {

    //调用Model发送获取验证码的网络请求的方法
    void sendProve(String stringAccount,String imie);

    //验证验证码是否正确
    void proveNumber(String stringAccount,String stringNumber,String imie);

    //设置短信验证码已经失效
    void setLoseEffect();

}
