package com.example.basecomponent.Services;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.Modules.BookModule;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface AddBookService {

    @PUT("book")
    Observable<BaseModule<BookModule>> putBook(@Header("Authorization") String authorization,
                                               @Body RequestBody body);
}
