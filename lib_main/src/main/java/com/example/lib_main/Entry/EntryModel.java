package com.example.lib_main.Entry;

import android.util.Log;

import com.example.basecomponent.CallBack;
import com.example.basecomponent.Excutes.LoginExcute;
import com.example.basecomponent.Modules.LoginModule;
import com.example.lib_main.Base.BaseModel;
import com.example.lib_main.Tool.EntryAccount;
import com.google.gson.Gson;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class EntryModel implements BaseModel<LoginModule> {

    private static final String TAG = "EntryModel";

    private String url = "http://47.95.207.40/branch/login";


    @Override
    public void execute(
            Callback callback,String... args) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        EntryAccount entryAccount = new EntryAccount();
        String stringAccount = args[0];
        String stringPassword = args[1];
        entryAccount.setAccount(stringAccount);
        entryAccount.setPassWord(stringPassword);
        Gson gson = new Gson();
        String json = gson.toJson(entryAccount);
        Log.d(TAG,"json"+json);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);

        Request request=new Request.Builder().post(requestBody).url(url)
                .addHeader("deviceId",args[3])
                .addHeader("validateCode",args[2])
                .addHeader("Authorization","Basic YnJhbmNoOnhpeW91M2c=")
                .build();
        okHttpClient.newCall(request).enqueue(callback);


    }

    @Override
    public void execute(CallBack<LoginModule> observer, String... args) {
        LoginExcute.loginexecute(observer,args[0],args[1],args[2],args[3]);
    }
}
