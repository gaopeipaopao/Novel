package com.example.basecomponent.Excutes;

import android.util.Log;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.LoginModule;
import com.example.basecomponent.Services.CodeService;
import com.example.basecomponent.Services.LoginService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginExcute {

    private static final String TAG = "LoginExcute";

    public static void loginexecute(Observer<LoginModule>
                                       loginobserver,String deviceId,String validateCode,
                               String account,String password){

        LoginService service = HttpUtil.getRetrofit().create(LoginService.class);

        JsonObject object = new JsonObject();
        object.addProperty("account",account);
        object.addProperty("password",password);
        Gson gson = new Gson();
        Log.d(TAG, "loginexecute: "+ gson.toJson(object));
        RequestBody requestBody = RequestBody.create(MediaType.
                parse("application/json;charset=UTF-8"),gson.toJson(object));

        service.login(deviceId,validateCode,HttpUtil.Authorization,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginobserver);


    }

    public static void CodeExcute(Observer<BaseModule> codeobserver,String deviceId){

        CodeService codeService = HttpUtil.getRetrofit().create(CodeService.class);
        codeService.verificateCode(deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(codeobserver);

    }
}
