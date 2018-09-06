package com.example.lib_main.Main.Home;

import android.content.Context;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.example.lib_main.Base.BasePresenter;
import com.example.lib_main.Tool.DataModel;
import com.example.lib_main.Tool.JudgeNetworkCoon;
import com.example.lib_main.Tool.Token;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomePresenter extends BasePresenter<HomeView> implements IHomePresenter {

    private static final String TAG = "HomePresenter";

    private Context context;
    private String newAccessToken = null;
    private String newRefreshToken = null;

    //判断网络是否可以使用
    private Boolean isNetwork = false;

    public HomePresenter(Context context){
        this.context = context;
        Log.d(TAG,TAG);
    }

    @Override
    public void sendToken(final String refreshToken) {

        isNetwork = JudgeNetworkCoon.isNetworkAvailable(context);
        if (!isNetwork){
            if (isViewAttached()){
                Log.d(TAG,"judgenet");
                getView().showNoNetwork();
                return;
            }
        }

        Log.d(TAG,"homePre333");
        DataModel.request(Token.HOME_MODEL).execute(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String response1 = response.body().string();
                Log.d(TAG,"444555:"+response1);
                int statusLen = 0;
                Log.d(TAG,response1);
                try {
                    JSONObject object = new JSONObject(response1);
                    statusLen = object.length();
                    if (statusLen == 5){
                        newAccessToken = object.getString("access_token");
                        newRefreshToken = object.getString("refresh_token");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (isViewAttached()){
                    getView().setToken(newAccessToken,newRefreshToken);
                }

                Message message = Message.obtain();
                message.what = 0;
                HomeActivity.handlers.sendEmptyMessageAtTime( message.what, SystemClock.uptimeMillis() + 1000 * 7195);
                Log.d(TAG,"1666");
            }
        },refreshToken);
    }
}
