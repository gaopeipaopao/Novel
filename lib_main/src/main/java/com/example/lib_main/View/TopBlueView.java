package com.example.lib_main.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 登录页面和个人页面的底部蓝色圆弧背景
 */

public class TopBlueView extends View{

    private static final String TAG = "TopBlueView";

    private Paint paint;
    private android.graphics.Path path;

    public TopBlueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.parseColor("#26a69a"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        path = new android.graphics.Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.d(TAG,""+getHeight());
        path.moveTo(0,0);
        path.lineTo(0 , getHeight() - 50);
        path.quadTo(getWidth() / 2,getHeight() ,getWidth(),getHeight() - 50);
        path.lineTo(getWidth(),0);
        path.close();
        canvas.drawPath(path,paint);
    }
}
