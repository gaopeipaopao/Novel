package com.example.basecomponent.Excutes;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.BookModule;
import com.example.basecomponent.Services.AddBookService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddExcute {

    public static  final String UNPUBLISHED = "UNPUBLISHED";
    public static final String PUBLISHED = "PUBLISH";

    public static void execute(final CallBack<BookModule> callBack,
                               final BookModule bookModule, String status){

        AddBookService service = HttpUtil.getRetrofit().create(AddBookService.class);

        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("bookName",bookModule.getBookName());
        object.addProperty("bookType",bookModule.getBookType());
        object.addProperty("bookIntroduce",bookModule.getContent());
        object.addProperty("firstTitle","");
        object.addProperty("firstContent","");
        object.addProperty("firstSummary","");
        object.addProperty("status",status);
        String s = gson.toJson(object);


        RequestBody body =  RequestBody.create(MediaType.
                parse("application/json;charset=UTF-8"),s);

        service.putBook(HttpUtil.Authorization+HttpUtil.getAccessToken(),
                body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModule<BookModule>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseModule<BookModule> bookModuleBaseModule) {
                        if(bookModuleBaseModule.getStatus() == 0){
                            callBack.onNext(bookModuleBaseModule.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
