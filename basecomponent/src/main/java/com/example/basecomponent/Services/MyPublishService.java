package com.example.basecomponent.Services;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;

public interface MyPublishService {


    @GET("/user/book")
    BaseModule<List<MyPublishModule>> getMyPublishData(@Header("token") String token);
}
