package com.example.basecomponent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.reactivex.schedulers.Schedulers;

public class  BaseModule<T> implements Serializable {

    @SerializedName("status")
    private int mStatus;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("data")
    private T mData;

    public int getStatus() {
        return mStatus;
    }

    public T getData() {
        return mData;
    }

    public String getMessage() {
        return mMessage;
    }
}
