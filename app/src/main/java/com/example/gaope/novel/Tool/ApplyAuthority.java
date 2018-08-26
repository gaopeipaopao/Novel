package com.example.gaope.novel.Tool;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * 权限申请,兼容8.0，申请权限组
 */

public class ApplyAuthority {

    public static int REQUEST_CODE = 1;

    //判断是否获得权限，获得返回true，没有获得返回false
    public static boolean judgeAuthority(Context context,String permission){

        if (ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
            return false;
        }else {
            return true;
        }

    }

    //申请权限
    public static void applyAuthority(Context context,String[] permissions){
        ActivityCompat.requestPermissions((Activity) context,permissions,REQUEST_CODE);
    }

}
