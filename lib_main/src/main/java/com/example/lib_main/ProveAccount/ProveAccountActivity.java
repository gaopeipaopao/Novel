package com.example.lib_main.ProveAccount;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lib_main.Base.BaseActivity;
import com.example.lib_main.R;
import com.example.lib_main.Register.RegisterActivity;
import com.example.lib_main.Tool.ActivityCollector;


/**
 * 验证昵称是否存在
 */
public class ProveAccountActivity extends BaseActivity implements ProveAccountView, View.OnClickListener, TextWatcher {

    private static final String TAG = "ProveAccountActivity";

    private EditText proveNameAccount;
    private Button proveNameButton;
    private ProveAccountPresenter proveAccountPresenter;
    private String stringProveNameAccount;
    private String phoneNumber;
    private String regiesterMessageKey;
    public static Handler handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prove_account);

        ActivityCollector.RegisterCollector.addActivity(this);

        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("手机号");
        regiesterMessageKey = intent.getStringExtra("key");

        proveAccountPresenter = new ProveAccountPresenter(getContexts());
        proveAccountPresenter.attachView(this);

        proveNameAccount = (EditText) findViewById(R.id.prove_account_name);
        proveNameButton = (Button) findViewById(R.id.prove_name_button);

        proveNameAccount.addTextChangedListener(this);

        proveNameButton.setOnClickListener(this);

        proveNameButton.setEnabled(Boolean.FALSE);

        handlers = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        Log.d(TAG,"what0");
                        proveFailAccount();
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
        proveAccountPresenter.detachView();
        ActivityCollector.RegisterCollector.removeActivity(this);
    }

    @Override
    public void onClick(View v) {
        stringProveNameAccount = proveNameAccount.getText().toString();
        proveAccountPresenter.judgeAccount(stringProveNameAccount);
    }

    @Override
    public void showNoNetwork() {
        Log.d(TAG,"base");
        Toast.makeText(getContexts(),"当前网络不可用，请检查网络连接",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void intentRegister() {
        Log.d(TAG,"startEntry");
        Intent intent = new Intent(ProveAccountActivity.this,
                RegisterActivity.class);
        intent.putExtra("手机号",phoneNumber);
        intent.putExtra("key",regiesterMessageKey);
        intent.putExtra("昵称",stringProveNameAccount);
        startActivity(intent);
    }

    @Override
    public void proveFailAccount() {
        Log.d(TAG,"startEntry");
        proveNameAccount.setText(null);
        Toast.makeText(getContexts(),"该用户名已存在",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAccountLength() {
        proveNameAccount.setText(null);
        Toast.makeText(getContexts(),"昵称必须在2到20字符之间",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(proveNameAccount.getText())) {
            proveNameButton.setEnabled(Boolean.FALSE);
            //Toast.makeText(getContexts(), "按钮不可点击", Toast.LENGTH_SHORT).show();
        } else {
            proveNameButton.setEnabled(Boolean.TRUE);
            //Toast.makeText(getContexts(), "按钮可点击", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
