package com.example.lib_main.Main.Mine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.style.IconMarginSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_main.Base.BaseFragment;
import com.example.lib_main.Main.Mine.MineInfor.MinePresenter;
import com.example.lib_main.Main.Mine.MineInfor.MineView;
import com.example.lib_main.R;
import com.example.lib_main.Tool.ApplyAuthority;
import com.example.lib_main.Tool.AuthorityGroup;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("ValidFragment")
public class MineFragment extends BaseFragment<MinePresenter> implements MineView, View.OnClickListener {

    private static final String TAG = "MineFragment";

    private Context context;
    private MinePresenter minePresenter;

    /**
     * 我的作品的LinearLayout
     */
    private LinearLayout mineBooks;

    /**
     * 顶部背景的Text2
     */
    private TextView backgroundText2;

    /**
     * 顶部背景的Text1
     */
    private TextView backgroundText1;

    /**
     * 顶部背景的图片
     */
    private CircleImageView topPicture;

    private ImageView mMyWork;
    private LinearLayout mClear;
    private LinearLayout mSetting;




    /**
     *
     * @param context
     */


    public MineFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        mineBooks = (LinearLayout) view.findViewById(R.id.mine_bookes);
        backgroundText1 = (TextView) view.findViewById(R.id.background_text_id1);
        backgroundText2 = (TextView) view.findViewById(R.id.background_text_id2);
        topPicture = (CircleImageView) view.findViewById(R.id.background_image_id);

        mClear = (LinearLayout)view.findViewById(R.id.mine_clear);
        mClear.setOnClickListener(this);
        mMyWork = (ImageView)view.findViewById(R.id.mine_book);
        mMyWork.setOnClickListener(this);
        mSetting = (LinearLayout)view.findViewById(R.id.mine_set);
        mSetting.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getPresenter().getMineInfor();

        topPicture.setImageResource(R.mipmap.mine_background_top_picture);

        mineBooks.setOnClickListener(this);

        topPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.background_image_id){

//                Intent intent = new Intent(context, CheckHeadPhotoActivity.class);
//                startActivity(intent);
                //choice();
//                if (ActivityCompat.checkSelfPermission(context,android.Manifest.permission.CAMERA)
//                        != PackageManager.PERMISSION_GRANTED){
//                    aaa();
//                }else {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivity(intent);
//                    Toast.makeText(context,"hh",Toast.LENGTH_SHORT).show();
//                }


        }else if(v.getId() == R.id.mine_book){
            ARouter.getInstance().build("/simple/myPublishActivity")
                    .navigation();

        }else if(v.getId() == R.id.mine_clear){


        }else if(v.getId() == R.id.mine_set){
            ARouter.getInstance().build("/account/SettingActivity")
                    .navigation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG,"hhhhhaaaaaxzzzzzzzzzzz");
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    intentCamera();
                }else if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                    //是否选择不再提醒
                    if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,permissions[0])){
                        promptDialog("摄像头");
                    }
                }
        }
    }

    private void choice(){
        //弹出一个对话框，让用户选择相册或者照相机
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String[] strings = new String[]{"拍照","相册"};
        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        // Log.d(TAG,"whit:"+which);
                        if (!ApplyAuthority.judgeAuthority(context, Manifest.permission.CAMERA)){
                            ApplyAuthority.applyAuthority(context, AuthorityGroup.CAMERA);
                        }else {
                            intentCamera();
                        }
                        break;
                    case 1:
                        // Log.d(TAG,"whit:"+which);
                        break;
                }
            }
        });
        builder.show();
    }

    private void intentCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    //当用户选择了不再提醒后，还要继续使用该功能，就弹出Dialog进行引导提示
    private void promptDialog(String string){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("无法使用" + string);
        builder.setMessage("已禁止使用" + string + ",如有功能异常可在设置功能中进行设置修改");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent =new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"权限获取失败",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    @Override
    public void aa() {
        //Toast.makeText(context,"aaa",Toast.LENGTH_SHORT).show();
    }
}
//打开摄像头时为禁止,不再访问https://www.jb51.net/article/111136.htm
//https://blog.csdn.net/qq_37664986/article/details/79304805
//6.0,7.0,8.0,手机测试
//6.0以下手机测试
//兼容8.0：申请整个组的权限，申请权限组，而不是单一的某个权限

