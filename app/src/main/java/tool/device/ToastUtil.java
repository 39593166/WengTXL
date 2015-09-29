package tool.device;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/3/12.
 * 提示框辅助类
 */
public class ToastUtil {

    /** 顯示提示信息 **/
    public static void showGenericToast(Context context,@StringRes int messageId){
        showGenericToast(context,context.getResources().getString(messageId));
    }

    /**
     * 显示默认的提示框
     * @param context
     * @param message
     */
    public static void showGenericToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示消息提示
     * @param context
     * @param message
     * @param gravity
     * @param during
     */
    public static void showToast(Context context,String message,int gravity,int during){
        Toast toast = Toast.makeText(context,message,during);
        toast.setGravity(gravity,0,0);
        toast.show();
    }

    /**
     * 显示消息提示
     * @param context
     * @param message
     * @param gravity
     */
    public static void showLongToast(Context context,String message,int gravity){
        showToast(context,message,gravity,Toast.LENGTH_LONG);
    }

    /**
     * 显示消息提示
     * @param context
     * @param message
     * @param gravity
     */
    public static void showShortToast(Context context,String message,int gravity){
        showToast(context,message,gravity,Toast.LENGTH_SHORT);
    }

}