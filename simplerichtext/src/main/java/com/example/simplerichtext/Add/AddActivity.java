package com.example.simplerichtext.Add;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.basecomponent.BaseModule;
import com.example.basecomponent.HttpUtil;
import com.example.basecomponent.Modules.MyPublishModule;
import com.example.basecomponent.PermissionUtil;
import com.example.basecomponent.Util;
import com.example.basecomponent.loading.LoadingUtil;
import com.example.simplerichtext.Base.BaseActivity;
import com.example.simplerichtext.Main.Holders.MyWorkHolder;
import com.example.simplerichtext.R;
import com.example.simplerichtext.Util.DialogUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


public class AddActivity extends BaseActivity implements View.OnClickListener,AddPersenter.AddViewInterface{

    private ImageView mBack;
    private ImageView mBookCover;
    private EditText mBookName;
    private RelativeLayout mBookBrief;
    private RelativeLayout mBookType;
    private AlertDialog mBackDialog;
    private final int PHOTO_CODE = 300;
    private final int BRIEF_CODE = 400;
    private final int TYPE_CODE = 500;
    private int mScreenWdith;
    private Uri mSelectedCover;
    private TextView mFinish;
    private String mBrief = "";
    private int mType = -1;
    private String mTypeName = "";
    private AddPersenter mPerseneter;
    private final int STORAGE_CODE = 200;
    private Dialog mLoadingView;

    private static final String TAG = "AddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity_add);
        DisplayMetrics metrics = new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWdith = metrics.widthPixels;
        //EventBus.getDefault().register(this);
        mPerseneter = new AddPersenter(this);
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
        mFinish = findViewById(R.id.tv_finish);
        mFinish.setOnClickListener(this);

    }

    private void showLoading(){
        if(mLoadingView== null){
            mLoadingView = LoadingUtil.showLoadingView(this);
        }
        mLoadingView.show();
    }

    private void dismissLoading(){
        if(mLoadingView!=null){
            mLoadingView.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_back){
           mBackDialog =  DialogUtil.CreateNomalDialog(AddActivity.this,
                    getResources().getString(R.string.simple_back),
                    getResources().getString(R.string.simple_back_message),true,
                    mBackListener
                    );
           mBackDialog.show();

        }else if(v.getId() == R.id.iv_book_bg){
            if(!EasyPermissions.hasPermissions(this,PermissionUtil.STORAGES)){
                PermissionUtil.
                        requestStoragePersmission(AddActivity.this, STORAGE_CODE);
            }else {
                Matisse.from(this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(1)
                        .gridExpectedSize(mScreenWdith/3-5)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .spanCount(3)
                        .thumbnailScale(0.85f) // 缩略图的比例
                        .theme(R.style.Matisse_Dracula)
                        .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                        .forResult(PHOTO_CODE); // 设置作为标记的请求码

            }


        }else if(v.getId() == R.id.relat_book_type){

            Intent intent = new Intent(this,BookTypeActivity.class);
            intent.putExtra("type",mType);
            startActivityForResult(intent,TYPE_CODE);

        }else if(v.getId() == R.id.relat_book_brief){

            Intent intent = new Intent(this,BookBriefActivity.class);
            intent.putExtra("brief",mBrief);
            startActivityForResult(intent,BRIEF_CODE);

        }else if(v.getId() == R.id.tv_finish){

            String name = mBookName.getText().toString().trim();
            if(!name.equals("")){
                if(mType!=-1){
                    String type = Util.getTypeKey(mTypeName);
                    if(!mBrief.equals("")){
                        //上传书
                        MyPublishModule book = new MyPublishModule();
                        book.setBookName(name);
                        book.setBookType(type);
                        book.setContent(mBrief);
                        mPerseneter.upload(book, Util.handleImage(this,mSelectedCover));
                        showLoading();
                    }else {
                        Toast.makeText(this,getResources()
                                .getText(R.string.simple_fill_brief),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,getResources()
                            .getText(R.string.simple_fill_type),Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,getResources()
                        .getText(R.string.simple_fill_name),Toast.LENGTH_SHORT).show();
            }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_CODE){
            if(EasyPermissions.hasPermissions(this,PermissionUtil.STORAGES)){
                Matisse.from(this)
                        .choose(MimeType.of(MimeType.valueOf("image")))
                        .countable(true)
                        .maxSelectable(1)
                        .gridExpectedSize(getResources().
                                getDimensionPixelSize(R.dimen.simple_book_cover_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f) // 缩略图的比例
                        .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                        .forResult(PHOTO_CODE); // 设置作为标记的请求码
            }else {
                Toast.makeText(this,R.string.simple_permission_deined,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == BRIEF_CODE && resultCode == RESULT_OK){
            mBrief = data.getStringExtra("brief");

        }

        if(requestCode == MyWorkHolder.PHOTO_CODE&&resultCode == RESULT_OK){
            List<Uri> images = Matisse.obtainResult(data);
                mSelectedCover = images.get(0);
            }


        if(requestCode == TYPE_CODE && resultCode == RESULT_OK){
            mType = data.getIntExtra("type",-1);
            mTypeName = data.getStringExtra("type_name");
            Log.d(TAG, "onActivityResult: "+mType);
        }
    }


    @Override
    public void updateData(MyPublishModule book) {
        if(book !=null){
            EventBus.getDefault().post(new AddBookMessage(book));
            dismissLoading();
            finish();
        }else {
            dismissLoading();
            uploadFailed(null);
        }
    }

    @Override
    public void uploadFailed(BaseModule module) {
        if(module!=null){
            Toast.makeText(this,module.getMessage(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,getResources().
                    getText(R.string.simple_upload_failed),Toast.LENGTH_SHORT).show();
        }

        dismissLoading();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
        mPerseneter.dettachView();
    }
}
