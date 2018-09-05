package com.example.basecomponent.Services;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;

public interface MyPublishService {


    @GET("user/book")
    Observable<BaseModule<List<MyPublishModule>>>
            getMyPublishData(@Header("Authorization") String token);
}
