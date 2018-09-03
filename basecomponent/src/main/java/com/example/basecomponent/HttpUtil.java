package com.example.basecomponent;



import io.reactivex.android.plugins.RxAndroidPlugins;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

    private static final String BASE_URL = "http://47.95.207.40/branch";
    private static String ACCESS_TOKEN = "";
    private static String REFRESH_TOKEN = "";

    private static Retrofit mClient = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();


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
