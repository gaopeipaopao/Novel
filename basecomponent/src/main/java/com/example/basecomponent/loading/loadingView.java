package com.example.basecomponent.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.basecomponent.R;

public class loadingView extends Dialog {

    private Context mContext;

    public loadingView(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public loadingView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    public loadingView(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.view_loading,null);
        setContentView(view);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//        getWindow().setAttributes(layoutParams);
    }
}
