package com.example.basecomponent.Modules;

import com.google.gson.annotations.SerializedName;

public class LoginModule {

    @SerializedName("status")
    private int mStatus;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("access_token")
    private String mAccessToken;
    @SerializedName("token_type")
    private String mTokenType;
    @SerializedName("refresh_token")
    private String mRefreshToken;
    @SerializedName("expires_in")
    private int  mExpiresIn;
    @SerializedName("scope")
    private String mUserType;

    public int getStatus() {
        return mStatus;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public int getExpiresIn() {
        return mExpiresIn;
    }

    public String getUserType() {
        return mUserType;
    }
}
