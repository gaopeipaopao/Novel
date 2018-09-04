package com.example.gaope.novel.Entry;

import android.util.Log;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Excutes.LoginExcute;
import com.example.gaope.novel.Base.BaseModel;
import com.example.gaope.novel.Tool.EntryAccount;
import com.google.gson.Gson;

import io.reactivex.Observer;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

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
    public void execute(Observer<BaseModule> observer, String... args) {
        LoginExcute.CodeExcute(observer,args[0]);
    }
}
