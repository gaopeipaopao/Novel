package com.example.gaope.novel.Entry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaope.novel.Base.BaseActivity;
import com.example.gaope.novel.Main.Home.HomeActivity;
import com.example.gaope.novel.Prove.ProveActivity;
import com.example.gaope.novel.R;

//登录界面的Activity

public class EntryActivity extends BaseActivity implements EntryView, View.OnClickListener, TextWatcher {

    private static final String TAG = "EntryActivity";

    private EntryPresenter entryPresenter;

    /**
     * 输入用户名的EditText
     */
    private EditText entryAccount;

    /**
     * 输入密码的EditText
     */
    private EditText entryPassword;
    private EditText entryNumber;

    private Button entryButton;
    private TextView entryRegister;
    private TextView entryForgetPassword;
    private String stringAccount;
    private String stringPassWord;
    private String stringNumber;
    public static Handler handlers;
    private CheckBox entryCheckBox;
    private boolean entryFrist;
    private ImageView entryImageRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        entryPresenter = new EntryPresenter(getContexts());
        entryPresenter.attachView(this);
        entryAccount = (EditText) findViewById(R.id.entry_account);
        entryPassword = (EditText) findViewById(R.id.entry_password);
        entryNumber = (EditText) findViewById(R.id.entry_number);
        entryButton = (Button) findViewById(R.id.entry_button);
        entryRegister = (TextView) findViewById(R.id.entry_register);
        entryForgetPassword = (TextView) findViewById(R.id.entry_forget_password);
        entryCheckBox = (CheckBox) findViewById(R.id.entry_checkbox);
        entryImageRefresh = (ImageView) findViewById(R.id.entry_image_number);
        entryImageRefresh.setScaleType(ImageView.ScaleType.CENTER_CROP);
        entryImageRefresh.setImageResource(R.mipmap.entry_refreshs);

        entryButton.setOnClickListener(this);
        entryRegister.setOnClickListener(this);
        entryForgetPassword.setOnClickListener(this);
        entryCheckBox.setOnClickListener(this);
        entryImageRefresh.setOnClickListener(this);

        entryAccount.addTextChangedListener(this);
        entryPassword.addTextChangedListener(this);
        entryNumber.addTextChangedListener(this);

        entryButton.setEnabled(Boolean.FALSE);

        if (!entryFrist){
            entryPresenter.imageNumber(returnIMEI());
            entryFrist = true;
        }

        handlers = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        Log.d(TAG,"what0");
                        showMistake();
                        break;
                    case 1:
                        Bitmap response = (Bitmap) msg.obj;
                        laodImageNumber(response);
                        break;
                    case 2:
                        showMistakeNumber();
                        break;
                }
            }
        };
    }

    /**
     * 点击空白时隐藏软键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //isShouldHideInput(v, ev)与hideInputMethod(this, v)的实现在BaseActivity中
            if (isShouldHideInput(v, ev)) {
                if(hideInputMethod(this, v)) {
                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.d(TAG,"aaaA");
        entryPresenter.detachView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entry_button:
                Log.d(TAG,"登录Button");
                //在Presenter的sendEntry()方法中发送登录请求
                stringAccount = entryAccount.getText().toString();
                stringPassWord = entryPassword.getText().toString();
                stringNumber = entryNumber.getText().toString();
                Log.d(TAG,"account:"+stringAccount.length());
                Log.d(TAG,"password:"+stringPassWord);
                entryPresenter.sendEntry(stringAccount,stringPassWord,
                        stringNumber,returnIMEI());
                break;
            case R.id.entry_checkbox:
                if(entryCheckBox.isChecked()){
                    entryPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    entryCheckBox.setButtonDrawable(R.mipmap.entry_eye_open);
                }else {
                    entryPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    entryCheckBox.setButtonDrawable(R.mipmap.entry_eye_close);
                }
                break;
            case R.id.entry_image_number:
                entryPresenter.imageNumber(returnIMEI());
                break;
            case R.id.entry_register:
                Log.d(TAG,"注册");
                //打开注册活动
                Intent intent = new Intent(EntryActivity.this, ProveActivity.class);
                startActivity(intent);
                finish();
                break;
            case  R.id.entry_forget_password:
                Log.d(TAG,"忘记密码");
                //打开忘记密码活动
                finish();
                break;
        }
    }



    @Override
    public void showGetNumber() {
        entryNumber.setText(null);
        Toast.makeText(getContexts(),"先要获得验证码",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMistake() {
        entryAccount.setText(null);
        entryPassword.setText(null);
        entryNumber.setText(null);
        entryPresenter.imageNumber(returnIMEI());
        Toast.makeText(getContexts(),"用户名或密码错误",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMistakeNumber() {
        entryNumber.setText(null);
        entryPresenter.imageNumber(returnIMEI());
        Toast.makeText(getContexts(),"验证码错误",Toast.LENGTH_LONG).show();
    }

    @Override
    public void intentNewActivity(String accessToken,String refreshToken) {
        Log.d(TAG,"intent");
        Intent intent = new Intent(EntryActivity.this, HomeActivity.class);
        intent.putExtra("access_token",accessToken);
        intent.putExtra("refresh_token",refreshToken);
        startActivity(intent);
        finish();
    }

    @Override
    public void laodImageNumber(Bitmap response) {
        Log.d(TAG,"laodImage:");
        entryImageRefresh.setImageBitmap(response);
    }

    //提醒用户网络没有连接
    @Override
    public void showNoNetwork() {
        Log.d(TAG,"base");
        Toast.makeText(getContexts(),"当前网络不可用，请检查网络连接",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDefaultImage() {
        entryImageRefresh.setImageResource(R.mipmap.entry_refreshs);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(entryNumber.getText()) ||
                TextUtils.isEmpty(entryPassword.getText())||
                TextUtils.isEmpty(entryNumber.getText())) {
            entryButton.setEnabled(Boolean.FALSE);
            //Toast.makeText(getContexts(), "按钮不可点击", Toast.LENGTH_SHORT).show();
        } else {
            entryButton.setEnabled(Boolean.TRUE);
            //Toast.makeText(getContexts(), "按钮可点击", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
