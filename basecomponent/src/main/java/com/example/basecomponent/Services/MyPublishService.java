package com.example.basecomponent.Services;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.R;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyPublishService {


    @GET("user/book")
    Observable<BaseModule<List<MyPublishModule>>>
            getMyPublishData(@Header("Authorization") String token,
                             @Query("status") String status);
}
