package com.example.gaope.novel;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestTest {
    public static void main(String[] args) throws IOException {
        String url = "http://47.95.207.40/branch/code/phone";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody formBody = new FormBody.Builder()
                .add("phoneNum", "13572011907")
                .build();
        Request request=new Request.Builder().url(url)
                .method("GET", formBody)
                .addHeader("deviceId","aaaaaaa")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String string = response.body().string();
        System.out.println(string);
    }
}
