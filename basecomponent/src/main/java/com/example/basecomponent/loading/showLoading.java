package com.example.basecomponent.loading;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.example.basecomponent.R;

public class showLoading {

    public static Dialog showLoadingView(Context context){

        loadingView loadingView = new loadingView(context,
                R.style.loading_style);

        return loadingView;

    }
}
