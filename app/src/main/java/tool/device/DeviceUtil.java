package tool.device;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by bj on 2015/7/29.
 * 设备信息辅助类
 */
public class DeviceUtil {

    /** 获取屏幕信息 **/
    public static DisplayMetrics getScreenInfo(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /** 获取屏幕宽度 **/
    public static int getScreenWidth(Context context){
        return getScreenInfo(context).widthPixels;
    }

    /** 获取屏幕高度 **/
    public static int getScreenHeight(Context context){
        return getScreenInfo(context).heightPixels;
    }

    /**  获取状态栏高度  **/
    public static int getStateBarHeight(Window window){
        Rect rect = new Rect();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    /** 获取手机号码信息 **/
    public static String getPhoneNumber(Context context){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    /** 获取设备的IMEI信息 **/
    public static String getPhoneIMEI(Context context){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /** 拨打电话号码 **/
    public static void callPhoneNumber(Context context,String phoneNumber,boolean isShowDial){
        Intent intent = new Intent(isShowDial?Intent.ACTION_DIAL:Intent.ACTION_CALL);
        intent.setData(Uri.parse(String.format("tel:%s",phoneNumber)));
        context.startActivity(intent);
    }

    /** 当前网络是否可用 **/
    public static boolean isNetAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info==null?false:info.isAvailable();
    }

    /** 获取当前可用的网络类型,-1为不可用网络 **/
    public static int getAvailableNetworkType(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info!=null) info.getType();
        return -1;
    }

}
