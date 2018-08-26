package com.example.gaope.novel.Register;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gaope.novel.Base.BaseActivity;
import com.example.gaope.novel.Entry.EntryActivity;
import com.example.gaope.novel.R;
import com.example.gaope.novel.Tool.ActivityCollector;
import com.example.gaope.novel.Tool.Token;

/**
 * 注册界面的Activity
 */

public class RegisterActivity extends BaseActivity implements RegisterView, TextWatcher{


    private static final String TAG = "RegisterActivity";

    private RegisterPresenter registerPresenter;
    private EditText registerPassword;
    private EditText registerPasswordAgain;
    private Button registerButton;
    private String stringPassword;
    private String stringPasswordAgain;
    private String phoneNumber;
    public static Handler handlers;
    private String regiesterMessageKey;
    private String nameAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActivityCollector.RegisterCollector.addActivity(this);

        registerPresenter = new RegisterPresenter(getContexts());
        registerPresenter.attachView(this);


        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("手机号");
        regiesterMessageKey = intent.getStringExtra("key");
        nameAccount = intent.getStringExtra("昵称");

        registerPassword = (EditText) findViewById(R.id.register_password);
        registerPasswordAgain = (EditText) findViewById(R.id.register_password_again);
        registerButton = (Button) findViewById(R.id.register_button);

        registerPassword.addTextChangedListener(this);
        registerPasswordAgain.addTextChangedListener(this);

        registerButton.setEnabled(Boolean.FALSE);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringPassword = registerPassword.getText().toString();
                stringPasswordAgain = registerPasswordAgain.getText().toString();
                registerPresenter.sendRegister(phoneNumber,nameAccount,stringPassword,
                        stringPasswordAgain,regiesterMessageKey,returnIMEI());
            }
        });

        handlers = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case  0:
                        Log.d(TAG,"what1");
                        intentEntry();
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
        registerPresenter.detachView();
        ActivityCollector.RegisterCollector.removeActivity(this);
    }

    @Override
    public void showNoNetwork() {
        Log.d(TAG,"base");
        Toast.makeText(getContexts(),"当前网络不可用，请检查网络连接",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPasswordLength() {
        registerPassword.setText(null);
        registerPasswordAgain.setText(null);
        Toast.makeText(getContexts(),"密码必须在6到30字符之间",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void judgePassword() {
        registerPassword.setText(null);
        registerPasswordAgain.setText(null);
        Toast.makeText(getContexts(),"两次密码的输入不一致",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void intentEntry() {
        Log.d(TAG,"startEntry");
        Toast.makeText(getContexts(),"注册成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, EntryActivity.class);
        startActivity(intent);
        ActivityCollector.RegisterCollector.finishAll();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(registerPassword.getText())||
                TextUtils.isEmpty(registerPasswordAgain.getText())) {
            registerButton.setEnabled(Boolean.FALSE);
            //Toast.makeText(getContexts(), "按钮不可点击", Toast.LENGTH_SHORT).show();
        } else {
            registerButton.setEnabled(Boolean.TRUE);
            //Toast.makeText(getContexts(), "按钮可点击", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
