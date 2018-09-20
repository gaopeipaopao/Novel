package com.example.basecomponent.loading;

import android.app.Dialog;
import android.content.Context;

import com.example.basecomponent.R;

public class LoadingUtil {

    public static Dialog showLoadingView(Context context){

        LoadingView loadingView = new LoadingView(context,
                R.style.loading_style);

        return loadingView;

    }
}
