package com.example.basecomponent.Excutes;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.WriteModule;
import com.example.basecomponent.Services.WriteService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class WriteExcute {

    public static void executeWrite(String status,
                                    final CallBack<BaseModule<List<WriteModule>>> callBack){

        WriteService service = HttpUtil.getRetrofit().create(WriteService.class);
        service.getWrite(HttpUtil.Bearer+HttpUtil.getAccessToken(),
                status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModule<List<WriteModule>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseModule<List<WriteModule>> listBaseModule) {
                        callBack.onNext(listBaseModule);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof HttpException) {
                            try {
                                HttpException httpException = (HttpException) e;
                                Gson gson = new Gson();
                                ResponseBody body = httpException.response().errorBody();
                                if (body != null) {
                                    String erro = body.string();
                                    BaseModule module = gson.fromJson(erro, BaseModule.class);
                                    callBack.onError(module);
                                } else {
                                    callBack.onError(null);
                                }
                            } catch (IOException ee) {
                                ee.printStackTrace();
                                callBack.onError(null);
                            }

                        }else {
                            callBack.onError(null);
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
