package com.example.gaope.novel.Main.Home;

import android.util.Log;

import com.example.basecomponent.CallBack;
import com.example.gaope.novel.Base.BaseModel;
import com.example.gaope.novel.Tool.EntryAccount;
import com.google.gson.Gson;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HomeModel implements BaseModel {

    private static final String TAG = "HomeModel";

    private String url = "http://47.95.207.40/branch/oauth/token";

    @Override
    public void execute(Callback callback, String... args) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type","refresh_token")
                .add("refresh_token",args[0])
                .build();

        Request request=new Request.Builder().post(requestBody).url(url)
                .addHeader("Authorization","Basic YnJhbmNoOnhpeW91M2c=")
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    @Override
    public void execute(CallBack observer, String... args) {

    }
}
