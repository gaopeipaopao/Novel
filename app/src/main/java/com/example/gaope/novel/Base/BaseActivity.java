package com.example.gaope.novel.Base;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gaope.novel.R;
import com.example.gaope.novel.Tool.JudgeNetworkCoon;
import com.example.gaope.novel.Tool.MatrixTool;
import com.example.gaope.novel.Tool.PhoneMac;

import java.io.File;

public class BaseActivity extends AppCompatActivity implements BaseView {

    private static final String TAG = "BaseActivity";

    //获取设备的IMEI号
    private String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        Log.d(TAG,"internal:"+getContexts().getFilesDir().getAbsolutePath());
//        Log.d(TAG,"internalCache:"+getContexts().getCacheDir().getAbsolutePath());
//        Log.d(TAG,"internalFile:"+getContexts().getDir("myFile",MODE_PRIVATE).getAbsolutePath());
//        Log.d(TAG,"external:"+getContexts().getExternalFilesDir("").getAbsolutePath());
//        Log.d(TAG,"externalSt:"+Environment.getExternalStorageDirectory());
//        Log.d(TAG,"root:"+Environment.getRootDirectory());
//        File[] files;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            files = getExternalFilesDirs(Environment.MEDIA_MOUNTED);
//            for (File file : files){
//                Log.d(TAG,"file:"+file);
//            }
//        }

        imei = PhoneMac.id(getContexts());
        Log.d(TAG,"imie:"+imei);


        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);


        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();

            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            );
        }
    }

    @Override
    public Context getContexts() {
        //     Log.d("base",":"+BaseActivity.this);
        return BaseActivity.this;
    }



    public static Boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }

    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0], top = leftTop[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //返回IMEI号
    public String returnIMEI(){
        Log.d(TAG,"miei:"+imei);
        return imei;
    }

}
