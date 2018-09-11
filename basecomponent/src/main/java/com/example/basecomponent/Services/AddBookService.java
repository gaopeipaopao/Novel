package com.example.basecomponent.Services;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.MyPublishModule;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AddBookService {

    @PUT("book")
    Observable<BaseModule<MyPublishModule>> putBook(@Header("Authorization") String authorization,
                                                    @Body RequestBody body);

    @POST("upload/book_image/{bookId}")
    @Multipart
    Observable<BaseModule> putCover(@Path("bookId") int bookId,
                                    @Part MultipartBody.Part file);
}
