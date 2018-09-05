package com.example.basecomponent.Excutes;

import android.os.Message;
import android.util.Log;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.LoginModule;
import com.example.basecomponent.Services.CodeService;
import com.example.basecomponent.Services.LoginService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.HttpRetryException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.HttpException;

public class LoginExcute {

    private static final String TAG = "LoginExcute";

    public static void loginexecute(final CallBack<LoginModule>
                                       loginoCallback , String deviceId, String validateCode,
                                    String account, String password){

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
                .subscribe(new Observer<LoginModule>() {

                               @Override
                               public void onSubscribe(Disposable d) {
                                   loginoCallback.onSubscribe();
                               }

                               @Override
                               public void onNext(LoginModule value) {
                                    loginoCallback.onNext(value);

                               }

                               @Override
                               public void onError(Throwable e) {

                                   if(e instanceof HttpException){
                                       HttpException httpException = (HttpException)e;
                                       try {
                                           String response = httpException.response().errorBody().string();
                                           Gson gson1 = new Gson();
                                           LoginModule module = gson1.fromJson(response,LoginModule.class);
                                           loginoCallback.onError(module);
                                       }catch (IOException ee){
                                           ee.printStackTrace();
                                       }
                                   }else {
                                       loginoCallback.onError(null);
                                   }


                               }

                               @Override
                               public void onComplete() {

                                   loginoCallback.onComplete();

                               }
                           });

    }

    public static void CodeExcute(CallBack<BaseModule> codeobserver,String deviceId){


    }
}
