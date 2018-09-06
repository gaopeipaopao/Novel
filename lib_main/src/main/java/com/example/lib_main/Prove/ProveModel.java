package com.example.lib_main.Prove;

import android.util.Log;

import com.example.basecomponent.CallBack;
import com.example.lib_main.Base.BaseModel;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ProveModel implements BaseModel {

    private static final String TAG = "ProveModel";

    private String url = "http://47.95.207.40/branch/code/phone?phoneNum=";

    @Override
    public void execute(Callback callback, String... args) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        String imie = args[1];
        Log.d(TAG,"imie:"+imie);
        Request request=new Request.Builder().url(url + args[0])
                .addHeader("deviceId",imie)
                .build();
        okHttpClient.newCall(request).enqueue(callback);

    }

    @Override
    public void execute(CallBack observer, String... args) {

    }
}
