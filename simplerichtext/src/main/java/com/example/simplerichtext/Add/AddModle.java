package com.example.simplerichtext.Add;

import android.net.Uri;

import com.example.basecomponent.CallBack;
import com.example.basecomponent.Excutes.AddExcute;
import com.example.basecomponent.Modules.BookModule;
import com.example.basecomponent.Modules.MyPublishModule;


public class AddModle implements AddPersenter.AddModleInterface {

    private AddPersenter mPersenter;

    public AddModle(AddPersenter persenter) {
        this.mPersenter = persenter;
    }

    @Override
    public void uploadBook(BookModule book, Uri image) {

        AddExcute.execute(new CallBack<BookModule>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(BookModule value) {
                mPersenter.updateData(value);
            }

            @Override
            public void onError(BookModule e) {

            }

            @Override
            public void onComplete() {

            }
        },book,AddExcute.UNPUBLISHED);
    }
}
