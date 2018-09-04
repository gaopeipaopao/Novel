package com.example.basecomponent.Services;

import com.example.basecomponent.BaseModule;
import com.google.gson.annotations.SerializedName;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CodeService {

    @GET("code/image")
    Observable<BaseModule> verificateCode(@Header("deviceId") String deviceId);
}
