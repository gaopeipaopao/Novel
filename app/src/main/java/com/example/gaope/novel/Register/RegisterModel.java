package com.example.gaope.novel.Register;

import android.util.Log;

import com.example.gaope.novel.Base.BaseModel;
import com.example.gaope.novel.Tool.EntryAccount;
import com.example.gaope.novel.Tool.RegisterAccount;
import com.google.gson.Gson;

import io.reactivex.Observer;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RegisterModel implements BaseModel {

    private static final String TAG = "RegisterModel";

    private String url = "http://47.95.207.40/branch/user/register";

    private RegisterAccount registerAccount = new RegisterAccount();

    @Override
    public void execute(Callback callback, String... args) {

        registerAccount.setUsername(args[0]);
        registerAccount.setAccount(args[1]);
        registerAccount.setPassword(args[2]);

        Gson gson = new Gson();
        String json = gson.toJson(registerAccount);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Log.d(TAG,"json"+json);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);

        Request request=new Request.Builder().post(requestBody).url(url)
                .addHeader("deviceId",args[4])
                .addHeader("key",args[3])
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    @Override
    public void execute(Observer observer, String... args) {

    }
}
