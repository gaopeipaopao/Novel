package com.example.gaope.novel;

import android.util.Log;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //@Test
//    public void test() throws IOException {
//        String url = "http://47.95.207.40/branch/code/phone?phoneNum=13572011907";
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//
//        Request request=new Request.Builder().url(url)
//                .addHeader("deviceId","aaaaaaa")
//                .get()
//                .build();
//        Response response = okHttpClient.newCall(request).execute();
//        String string = response.body().string();
//        System.out.println(string);
//    }
}