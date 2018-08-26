package com.example.gaope.novel.Register;

public interface IRegisterPresenter {

    //发送注册请求
    void sendRegister(String phoneNumber,
                      String stringAccount, String stringPassword,
                      String stringPasswordAgain,
                      String regiesterMessageKey,
                      String imie);

}
