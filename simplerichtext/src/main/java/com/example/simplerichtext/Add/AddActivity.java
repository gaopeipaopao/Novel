package com.example.simplerichtext.Add;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.R;
import com.example.simplerichtext.Util.DialogUtil;

import org.w3c.dom.Text;


public class AddActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mBack;
    private ImageView mBookCover;
    private EditText mBookName;
    private RelativeLayout mBookBrief;
    private RelativeLayout mBookType;
    private AlertDialog mBackDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_add);
        init();
    }

    private void init(){
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        mBookCover = findViewById(R.id.iv_book_bg);
        mBookCover.setOnClickListener(this);
        mBookType = findViewById(R.id.relat_book_type);
        mBookType.setOnClickListener(this);
        mBookBrief = findViewById(R.id.relat_book_brief);
        mBookBrief.setOnClickListener(this);
        mBookName = findViewById(R.id.edit_book_name);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_back){
           mBackDialog =  DialogUtil.CreateNomalDialog(AddActivity.this,
                    getResources().getString(R.string.simple_back),
                    getResources().getString(R.string.simple_back_message),true,
                    mBackListener
                    );

        }else if(v.getId() == R.id.iv_book_bg){


        }else if(v.getId() == R.id.relat_book_type){


        }else if(v.getId() == R.id.relat_book_brief){

        }
    }

    private AlertDialog.OnClickListener mBackListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(mBackDialog !=null){
                if(which == DialogInterface.BUTTON_NEGATIVE){
                    dialog.dismiss();
                }else if(which == DialogInterface.BUTTON_POSITIVE){
                    finish();
                }
            }
        }
    };
}
