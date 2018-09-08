package com.example.simplerichtext.Add;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.R;

public class BookTypeActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private ImageView mBack;
    private TextView mSave;
    private int mCheckedId;
    private static final String TAG = "BookTypeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_book_type);
        Intent intent = getIntent();
        mCheckedId = intent.getIntExtra("type",-1);
        init();
    }

    private void init(){
        mRadioGroup = findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(this);
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSave = findViewById(R.id.tv_save);
        mSave.setOnClickListener(this);
        Log.d(TAG, "init: "+mCheckedId);
        if(mCheckedId !=-1){
            mRadioGroup.check(mCheckedId);
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_save){
            Intent intent = new Intent();
            intent.putExtra("type",mCheckedId);
            setResult(RESULT_OK,intent);
            finish();

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.d(TAG, "onCheckedChanged: "+checkedId);
        mCheckedId = checkedId;
    }
}
