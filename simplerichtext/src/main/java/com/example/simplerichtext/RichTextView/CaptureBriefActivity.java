package com.example.simplerichtext.RichTextView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Util;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.R;

public class CaptureBriefActivity extends BaseActivity implements
        View.OnClickListener {

    private ImageView mBack;
    private TextView mSave;
    private EditText mEditText;
    private TextView mCount;
    private String mBrief;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_capture_brief);
        mBrief = getIntent().getStringExtra("brief");
        init();
    }

    private void init(){
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSave = findViewById(R.id.tv_save);
        mSave.setOnClickListener(this);
        mEditText = findViewById(R.id.et_brief);
        mCount = findViewById(R.id.tv_count);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCount.setText(String.valueOf(Util.getABCCount(s.toString())
                        + Util.getChCount(s.toString())));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEditText.setText(mBrief);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_save){
            if(Integer.parseInt(mCount.getText().toString())>=10){
                Intent intent = new Intent();
                intent.putExtra("brief",mEditText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }else {
                Toast.makeText(this,R.
                        string.simple_brief_limit,Toast.LENGTH_SHORT).show();
            }

        }
    }
}
