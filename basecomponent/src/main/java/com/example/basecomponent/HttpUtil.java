package com.example.basecomponent;

import java.io.IOException;
import java.nio.Buffer;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.plugins.RxAndroidPlugins;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

    private static final String BASE_URL = "http://47.95.207.40/branch/";
    public static final String BOOK_COVER = "http://47.95.207.40/branch/file/book/";
    private static String ACCESS_TOKEN = "";
    private static String REFRESH_TOKEN = "";
    public static String Authorization = "Basic YnJhbmNoOnhpeW91M2c=";
    public static String Bearer = "Bearer ";
    private static final long DEFAULT_TIMEOUT = 60;
    public  static final String REQUEST_CONTENT = "application/json;charset=UTF-8";


    private static OkHttpClient OKClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder builder = request.newBuilder();
                    request = builder
                            .build();

                    return chain.proceed(request);
                }
            })
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    private static Retrofit mClient = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OKClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final okio.Buffer buffer = new okio.Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
        public static Retrofit getRetrofit(){

       return mClient;
   }

   public static String getAccessToken(){
       return  ACCESS_TOKEN;
   }

   public static void setAccessToken(String accessToken){
       ACCESS_TOKEN = accessToken;
   }

   public static String getRefreshToken(){
       return REFRESH_TOKEN;
   }

   public static void setRefreshToken(String refreshToken){
       REFRESH_TOKEN = refreshToken;
   }


}
