package com.example.basecomponent.Modules;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthorModule implements Serializable {

    @SerializedName("userId")
    private int mUserId;

    @SerializedName("username")
    private String mUserName;

    @SerializedName("account")
    private String mAccount;

    @SerializedName("icon")
    private String mIcon;

    @SerializedName("signtext")
    private String mSignText;

    public int getUserId() {
        return mUserId;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getAccount() {
        return mAccount;
    }

    public String getIcon() {
        return mIcon;
    }

    public String getSignText() {
        return mSignText;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public void setUserName(String UserName) {
        this.mUserName = mUserName;
    }

    public void setAccount(String Account) {
        this.mAccount = mAccount;
    }

    public void setIcon(String Icon) {
        this.mIcon = mIcon;
    }

    public void setmSignText(String SignText) {
        this.mSignText = mSignText;
    }
}
