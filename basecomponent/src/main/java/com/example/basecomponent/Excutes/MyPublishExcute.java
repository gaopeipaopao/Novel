package com.example.basecomponent.Excutes;

import com.example.basecomponent.BaseModule;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.Services.MyPublishService;

import java.util.List;


public class MyPublishExcute {

    public static void excute(){


        MyPublishService service = HttpUtil.getRetrofit().create(MyPublishService.class);

        service.getMyPublishData(HttpUtil.getAccessToken());


    }

}
