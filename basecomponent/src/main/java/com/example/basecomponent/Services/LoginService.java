package com.example.basecomponent.Services;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.LoginModule;
import com.google.gson.annotations.SerializedName;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Observable<LoginModule> login(@Header("deviceId") String deviceId,
                                  @Header("validateCode") String validateCode,
                                  @Header("Authorization") String authorization,
                                  @Body RequestBody requestBody
                                  );
}
