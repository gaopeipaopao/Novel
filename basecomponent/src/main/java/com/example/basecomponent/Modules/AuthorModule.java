package com.example.basecomponent.Modules;

import com.google.gson.annotations.SerializedName;

public class AuthorModule {

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
}
