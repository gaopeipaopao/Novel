package com.example.simplerichtext.Main;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class Test {

    private static String path = "C:/Users/asus1/Desktop/20160825114252491.png";

    public static void main(String[] args){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://diamond.creatshare.com")
                .build();
        File file = new File(path);

        RequestBody requestBody = RequestBody.create(MediaType.
                parse("multipart/form-data"),file);
        MultipartBody.Part body;

        body = MultipartBody.Part.
                    createFormData("image","image.png",requestBody);

        upload se = retrofit.create(upload.class);
        se.call(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

               try {
                   System.out.println(response.body().string());
               }catch (IOException e){

               }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t.toString());
            }
        });



    }

    public interface upload{

        @POST("/upload")
        @Multipart
        Call<ResponseBody> call(@Part MultipartBody.Part part);
    }
}
