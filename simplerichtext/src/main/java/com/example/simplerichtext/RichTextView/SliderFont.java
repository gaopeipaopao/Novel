package com.example.simplerichtext.RichTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.simplerichtext.R;
import com.example.simplerichtext.Util.ConstantUtil;

public class SliderFont extends View {

    private Context mContext;
    private Drawable mThumb;
    private Paint mThumbPaint;
    private Paint mProgressPaint;

    private int mWidth;
    private int mHeight;
    private  int i = 0;
    private int mFontSize = 19;
    private int mSpec = 0;
    private int mCenterX;
    private int mCenterY;
    private float mLastX;
    private float mLastY;
    private int mScreenHight;
    private int mScreemWidth;
    private int mSliderWidth;
    private int mOffsetLeft;
    private int mOffsetRight;
    private int mIndex = ConstantUtil.mFontIndex;
    private float[] fontSize = new float[]{
      12,14,16,19,22,24,26
    };


    private static final String TAG = "SliderFont";

    public SliderFont(Context context) {
        this(context,null);
    }

    public SliderFont(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SliderFont(Context context, @Nullable AttributeSet attrs
            , int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init(){
        mThumb = mContext.getResources().getDrawable(R.mipmap.ic_thumb);
        mThumbPaint = new Paint();
        mProgressPaint = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST){
            mWidth = 500;
        }else {
            mWidth = width;
        }

        if(heightMode == MeasureSpec.AT_MOST){
            mHeight = 50;
        }else {
            mHeight = height;
        }


        setMeasuredDimension(mWidth,mHeight);
    }

    public void setScreemWidth(int screemWidth){
        mScreemWidth = screemWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mProgressPaint.setColor(mContext.getResources().getColor(R.color.divide_color));
        mProgressPaint.setStyle(Paint.Style.FILL);
        for(i = 0;i<7;i++){
            canvas.drawRect(mSpec*i+mOffsetLeft,(mHeight-30)/2,mSpec*i+3f+mOffsetLeft,
                    (mHeight-30)/2+30,mProgressPaint);
            if(i!=6){
                canvas.drawRect(mSpec*i+mOffsetLeft,(mHeight-30)/2+15,mSpec*(i+1)+mOffsetLeft,
                        (mHeight-30)/2+18f,mProgressPaint);
            }

        }

        mThumb.setBounds(mCenterX-mHeight/2+5,mCenterY-mHeight/2+5,
                mCenterX+mHeight/2-5,mCenterY+mHeight/2-5);
        mThumb.draw(canvas);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mSliderWidth = mWidth -getPaddingLeft()-getPaddingRight();
        mOffsetLeft = getPaddingLeft();
        mSpec = mSliderWidth/6;
        mCenterX = mSpec*mIndex+mOffsetLeft;
        mCenterY = (mHeight-30)/2+15;
    }

    public void setCenter(float center){
        center =  center-(mScreemWidth-mWidth)/2;

        mCenterX = (int) (center);
    }

    public int adJustCenter(float local){
        local = local -(mScreemWidth-mWidth)/2;
        mIndex = (int) local/mSpec;
        if(mIndex<=0){
            mIndex = 0;
        }
        if(mIndex>=6){
            mIndex = 6;
        }
        mCenterX = mSpec*mIndex+mOffsetLeft;
        invalidate();

        return mIndex;
    }

    public float getFontSize(int index){
        return fontSize[index];
    }

    public int getIndex(){
        return  mIndex;
    }


    public void move(float spec){
        mCenterX+=spec;
        if(mCenterX<=mOffsetLeft){
            mCenterX = mOffsetLeft;
        }
        if(mCenterX >=mWidth-mOffsetLeft){
            mCenterX = mWidth-mOffsetLeft;
        }
    }


}
