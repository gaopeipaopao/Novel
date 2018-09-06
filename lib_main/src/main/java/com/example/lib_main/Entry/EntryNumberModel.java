package com.example.lib_main.Entry;

import android.util.Log;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.Excutes.LoginExcute;
import com.example.lib_main.Base.BaseModel;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class EntryNumberModel implements BaseModel<BaseModule> {

    private static final String TAG = "EntryNumberModel";

    private String url = "http://47.95.207.40/branch/code/image";

    @Override
    public void execute(Callback callback, String... args) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        String imie = args[0];
        Log.d(TAG,"imie:"+imie);
        Request request=new Request.Builder().url(url)
                .addHeader("deviceId",imie)
                .build();
        okHttpClient.newCall(request).enqueue(callback);

    }

    @Override
    public void execute(CallBack<BaseModule> observer, String... args) {
        LoginExcute.CodeExcute(observer,args[0]);
    }
}
