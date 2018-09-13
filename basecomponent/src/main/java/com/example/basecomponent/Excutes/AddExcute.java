package com.example.basecomponent.Excutes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.CallBack;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Services.AddBookService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.HttpException;

public class AddExcute {

    private static final String TAG = "AddExcute";

    public static  final String UNPUBLISHED = "UNPUBLISHED";
    public static final String PUBLISHED = "PUBLISH";

    public static void execute(final CallBack<MyPublishModule> callBack,
                               final MyPublishModule bookModule, final String image, String status){

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

        Log.d(TAG, "execute: "+s);
        RequestBody body =  RequestBody.create(MediaType.
                parse("application/json;charset=UTF-8"),s);
        Log.d(TAG, "execute: "+HttpUtil.getAccessToken());

        service.putBook(HttpUtil.Bearer+HttpUtil.getAccessToken(),
                body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModule<MyPublishModule>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseModule<MyPublishModule> myPublishModuleBaseModule) {
                        if(myPublishModuleBaseModule.getStatus() == 0
                                &&myPublishModuleBaseModule.getData()!=null){
//                            if(image!=null){
//                                uploadCover(myPublishModuleBaseModule.getData().getBookId(),
//                                        image,callBack);
//
//                            }
                                callBack.onNext(myPublishModuleBaseModule.getData());


                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: 1111   "+e.getMessage());
                        callBack.onError(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static void uploadCover(final int bookid, final String image, final CallBack<BaseModule> callBack){
        AddBookService service = HttpUtil.getRetrofit().create(AddBookService.class);

            File file = new File(image);
            RequestBody requestBody = RequestBody.create(MediaType.
                    parse("multipart/form-data"),file);
            MultipartBody.Part body = MultipartBody.Part.
                    createFormData("image",file.getName(),requestBody);
            service.putCover(bookid,body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseModule>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseModule baseModule) {

                            if(baseModule.getStatus() == 0){

                                callBack.onNext(null);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: "+e.getMessage());
                            callBack.onError(null);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }

    public static void updateBook(MyPublishModule bookModule, String status,
                                  final CallBack<BaseModule<MyPublishModule>> callBack){

        AddBookService service = HttpUtil.getRetrofit().create(AddBookService.class);

        final Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("bookId",bookModule.getBookId());
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

        service.putBook(HttpUtil.Bearer+HttpUtil.getAccessToken(),body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseModule<MyPublishModule>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseModule<MyPublishModule> myPublishModuleBaseModule) {
                        callBack.onNext(myPublishModuleBaseModule);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof HttpException){
                            try {
                                HttpException httpException = (HttpException)e;
                                String erro = httpException.response().errorBody().string();

                                BaseModule module
                                        = gson.fromJson(erro,BaseModule.class);
                                callBack.onError(module);
                            }catch (IOException e1){
                                e1.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
