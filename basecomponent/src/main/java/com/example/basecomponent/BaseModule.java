package com.example.basecomponent;

import com.google.gson.annotations.SerializedName;

public class  BaseModule<T> {

    @SerializedName("status")
    private int mStatus;

    @SerializedName("data")
    private T mData;

    public int getStatus() {
        return mStatus;
    }

    public T getData() {
        return mData;
    }
}
