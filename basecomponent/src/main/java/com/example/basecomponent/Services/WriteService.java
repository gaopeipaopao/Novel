package com.example.basecomponent.Services;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.WriteModule;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface WriteService {

    @GET("user/branch")
   Observable<BaseModule<List<WriteModule>>> getWrite(@Header("Authorization") String accessToken,
                                                     @Query("status") String status);
}
