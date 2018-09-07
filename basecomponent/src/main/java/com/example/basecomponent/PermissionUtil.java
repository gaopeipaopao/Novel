package com.example.basecomponent;


import android.Manifest;
import android.app.Activity;
import android.content.Context;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionUtil {

    private static String[] STORAGES = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void requestStoragePersmission(Activity activity,int requestCode){

        if (!EasyPermissions.hasPermissions(activity,STORAGES)){

            EasyPermissions.requestPermissions(activity,activity.getResources().
                    getString(R.string.request_permission),requestCode,STORAGES);
        }
    }
}
