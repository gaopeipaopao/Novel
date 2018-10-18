package com.example.basecomponent;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindow();
    }

    protected void setWindow(){
        Window window  = getWindow();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){

            View decorView = window.getDecorView();
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

                );
            }else {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
            }
        }else {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

        window.setStatusBarColor(Color.TRANSPARENT);
    }
}
