package com.example.gaope.novel.Tool;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * 帮助缩放图片的类
 */

public class MatrixTool {

    private static final String TAG = "MatrixTool";

    public static Bitmap matrixPhoto (Bitmap bitmap, int width, int height) {
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        float w = (float) (80.0/width);
        float h = (float) (80.0/height);
        matrix.setScale(w,h);
        Log.d(TAG,"wi"+width+"   he:"+height);
        Log.d(TAG,"w"+w+"   h:"+h);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        return bitmap1;
    }
}
