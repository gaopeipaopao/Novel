package com.example.gaope.novel.Prove;

import android.util.Log;

import com.example.gaope.novel.Base.BaseModel;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 发送请求判断验证码是否正确
 */

public class ProveNumberModel implements BaseModel {

    private static final String TAG = "ProveNumberModel";

    private String url = "http://47.95.207.40/branch/code/sms/verificate?phoneNum=";

    @Override
    public void execute(Callback callback, String... args) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        String imie = args[2];
        Log.d(TAG,"imie:"+imie);
        Request request=new Request.Builder().url(url + args[0])
                .addHeader("deviceId",imie)
                .addHeader("validateCode",args[1])
                .build();
        okHttpClient.newCall(request).enqueue(callback);

    }
}
