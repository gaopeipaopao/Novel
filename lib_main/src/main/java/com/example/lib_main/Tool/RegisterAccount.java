package com.example.lib_main.Tool;

/**
 * 登录时：发送登陆的网络请求时的Post包中的json账号名与密码的类
 */

public class RegisterAccount {

    public String username;
    public String account;
    public String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccount() {
        return account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

