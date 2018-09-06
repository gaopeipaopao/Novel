package com.example.lib_main.Register;

public interface IRegisterPresenter {

    //发送注册请求
    void sendRegister(String phoneNumber,
                      String stringAccount, String stringPassword,
                      String stringPasswordAgain,
                      String regiesterMessageKey,
                      String imie);

}
