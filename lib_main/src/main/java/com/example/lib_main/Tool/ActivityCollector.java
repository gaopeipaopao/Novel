package com.example.lib_main.Tool;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理活动的类
 */
public class ActivityCollector {


    //内部类管理注册过程的活动
    public static class RegisterCollector{
        public static List<Activity> activities = new ArrayList<>();

        //添加Activity
        public static void addActivity(Activity activity){
            activities.add(activity);
        }

        //移除Activity
        public static void removeActivity(Activity activity){
            activities.remove(activity);
        }

        public static void finishAll(){
            for (Activity activity : activities){
                if (!activity.isFinishing()){
                    activity.finish();
                }
            }
            activities.clear();
        }
    }
}
