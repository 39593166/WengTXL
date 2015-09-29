package tool.device;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

/**
 * Created by bj on 2015/7/29.
 * APK文件辅助类
 */
public class ApkUtil {


    /**  获取软件版本值 **/
    public static int getVersionCode(Context context){
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /** 获取软件版本名称 **/
    public static String getVersionName(Context context){
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 安装APK文件
     * @param context
     * @param filename 要安装的文件名
     */
    public static void installApk(Context context,String filename){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(filename)),"application/vnd.android.package-archive");
        context.startActivity(intent);
    }

}
