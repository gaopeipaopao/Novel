package com.example.gaope.novel.ProveAccount;

import android.util.Log;

import com.example.basecomponent.CallBack;
import com.example.gaope.novel.Base.BaseModel;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ProveAccountModel implements BaseModel {

    private static final String TAG = "ProveAccountModel";

    private String url = "http://47.95.207.40/branch/user/username/exist?username=";

    @Override
    public void execute(Callback callback, String... args) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request=new Request.Builder().url(url + args[0])
                .build();
        okHttpClient.newCall(request).enqueue(callback);

    }

    @Override
    public void execute(CallBack observer, String... args) {

    }
}
