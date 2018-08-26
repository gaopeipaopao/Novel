package com.example.gaope.novel.ProveAccount;

import com.example.gaope.novel.Base.BaseView;

public interface ProveAccountView extends BaseView{

    //检测网络是否连接
    void showNoNetwork();

    //提醒用户名称必须在2到20之间
    void showAccountLength();

    //注册成功，打开密码活动
    void intentRegister();

    //注册的用户名已经存在
    void proveFailAccount();
}
