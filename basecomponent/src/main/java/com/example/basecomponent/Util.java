package com.example.basecomponent;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Set;

public class Util {

    public static HashMap<String,String> TYPES = new HashMap<>();
    static {
        TYPES.put("FantasySentiment","玄幻言情");
        TYPES.put("ImmortalChivalry","仙侠奇缘");
        TYPES.put("AncientSentiment","古代言情");
        TYPES.put("ModernSentiment","现代言情");
        TYPES.put("RomanticYouth","浪漫青春");
        TYPES.put("SuspensePsychic","悬疑灵异");
        TYPES.put("ScienceSpace","科幻空间");
        TYPES.put("GameCompetition","游戏竞技");
        TYPES.put("TanbiNovel","耽美小说");

    }

    public static  int getABCCount(String s){
        int count = 0;
        for(int i=0;i<s.length();i++){
            char cs =s.charAt(i);
            if((cs>='a'&& cs<='z') || ((cs>='A'&& cs<='Z')) || ((cs>='0'&& cs<='9')) ){
                count++;
            }
        }
        return count;
    }

    public static  int getChCount(String s){

        int count =0;
        String Reg="^[\u4e00-\u9fa5]{1}$";  //汉字的正规表达式
        for(int i=0;i<s.length();i++){
            String b=Character.toString(s.charAt(i));
            if(b.matches(Reg))
                count++;
        }

        return count;
    }

    public static String getTypeValue(String key){

        return TYPES.get(key);
    }

    public static String getTypeKey(String value){

        Set<String> keys = TYPES.keySet();
        for(String key :keys){
            if(TYPES.get(key).equals(value)){
                return key;
            }
        }

        return "";
    }

    public static String handleImage(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())){
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }

        return filePath;

    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }



    /**
     * 判断网络是否连接
     */

    public static boolean isNetworkAvailable(Context context) {
            Context contexts = context;
            // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager == null)
            {
                return false;
            }
            else
            {
                // 获取NetworkInfo对象
                NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

                if (networkInfo != null && networkInfo.length > 0)
                {
                    for (int i = 0; i < networkInfo.length; i++)
                    {
                       // Log.d(TAG,"网络状态:"+networkInfo[i].getState());
                       // Log.d(TAG,"网络类型:"+networkInfo[i].getTypeName());
//                    System.out.println(i + "===状态===" + networkInfo[i].getState());
//                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                        // 判断当前网络状态是否为连接状态
                        if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                        {
                            return true;
                        }
                    }
                }
            }
            return false;
        }




}
