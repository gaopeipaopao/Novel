package com.example.simplerichtext.Add;

import android.net.Uri;

import com.example.basecomponent.CallBack;
import com.example.basecomponent.Excutes.AddExcute;
import com.example.basecomponent.Modules.MyPublishModule;


public class AddModle implements AddPersenter.AddModleInterface {

    private AddPersenter mPersenter;

    public AddModle(AddPersenter persenter) {
        this.mPersenter = persenter;
    }

    @Override
    public void uploadBook(MyPublishModule book, String image) {

        AddExcute.execute(new CallBack<MyPublishModule>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(MyPublishModule value) {
                mPersenter.updateData(value);
            }

            @Override
            public void onError(MyPublishModule e) {
                mPersenter.uploadFailed();
            }

            @Override
            public void onComplete() {

            }
        },book,image,AddExcute.UNPUBLISHED);
    }
}
