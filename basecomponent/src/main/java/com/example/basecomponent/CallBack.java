package com.example.basecomponent;

import com.example.basecomponent.Modules.LoginModule;

import io.reactivex.disposables.Disposable;

public interface CallBack<T> {


    void onSubscribe();


    void onNext(T value);




    void onError(T e);



    void onComplete();



}
