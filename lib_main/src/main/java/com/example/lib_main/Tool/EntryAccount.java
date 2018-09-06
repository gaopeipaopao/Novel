package com.example.lib_main.Tool;

/**
 * 登录时：发送登陆的网络请求时的Post包中的json账号名与密码的类
 */

public class EntryAccount {

    public String account;
    public String password;

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public String getPassWord() {
        return password;
    }
}
