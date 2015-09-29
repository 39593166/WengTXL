package tool.device;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 图像处理辅助类
 */
public class ImageUtil {

    /**
     * 请求从相册中获取图片
     * @param activity
     * @param requestCode
     */
    public static void pickImage(Activity activity,int requestCode){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 请求从相册中获取图片
     * @param fragment
     * @param requestCode
     */
    public static void pickImage(Fragment fragment,int requestCode){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 从相机中获取图片
     * @param activity
     * @param requestCode
     */
    public static void takePhoto(Activity activity,int requestCode){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 从相机中获取图片
     * @param fragment
     * @param requestCode
     */
    public static void takePhoto(Fragment fragment,int requestCode){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 进行裁剪的图像大小
     * @param activity
     * @param imgSize
     * @param uri
     * @param requestCode
     */
    public static void cropImage(Activity activity,int imgSize, Uri uri,File file,int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP" );
        intent.setDataAndType(uri, "image/*");
        intent.putExtra( "crop", "true");

        ////////////////////////////////////////////////////////////////
        // 1.宽高和比例都不设置时,裁剪框可以自行调整(比例和大小都可以随意调整)
        ////////////////////////////////////////////////////////////////
        // 2.只设置裁剪框宽高比(aspect)后,裁剪框比例固定不可调整,只能调整大小
        ////////////////////////////////////////////////////////////////
        // 3.裁剪后生成图片宽高(output)的设置和裁剪框无关,只决定最终生成图片大小
        ////////////////////////////////////////////////////////////////
        // 4.裁剪框宽高比例(aspect)可以和裁剪后生成图片比例(output)不同,此时,
        //  会以裁剪框的宽为准,按照裁剪宽高比例生成一个图片,该图和框选部分可能不同,
        //  可能是截取框选的一部分,也可能超出框选部分,由框选部分顶端向下延伸补足
        ////////////////////////////////////////////////////////////////

        // aspectX aspectY 是裁剪框宽高的比例
        intent.putExtra( "aspectX", 1);
        intent.putExtra( "aspectY", 1);
        // outputX outputY 是裁剪后生成图片的宽高
        intent.putExtra( "outputX", imgSize);
        intent.putExtra( "outputY", imgSize);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra( "return-data", true);
        activity.startActivityForResult(intent, requestCode);
    }


    /**
     * 进行裁剪的图像大小
     * @param fragment
     * @param imgSize
     * @param uri
     * @param file
     * @param requestCode
     */
    public static void cropImage(Fragment fragment,int imgSize, Uri uri,File file,int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP" );
        intent.setDataAndType(uri, "image/*");
        intent.putExtra( "crop", "true");

        ////////////////////////////////////////////////////////////////
        // 1.宽高和比例都不设置时,裁剪框可以自行调整(比例和大小都可以随意调整)
        ////////////////////////////////////////////////////////////////
        // 2.只设置裁剪框宽高比(aspect)后,裁剪框比例固定不可调整,只能调整大小
        ////////////////////////////////////////////////////////////////
        // 3.裁剪后生成图片宽高(output)的设置和裁剪框无关,只决定最终生成图片大小
        ////////////////////////////////////////////////////////////////
        // 4.裁剪框宽高比例(aspect)可以和裁剪后生成图片比例(output)不同,此时,
        //  会以裁剪框的宽为准,按照裁剪宽高比例生成一个图片,该图和框选部分可能不同,
        //  可能是截取框选的一部分,也可能超出框选部分,由框选部分顶端向下延伸补足
        ////////////////////////////////////////////////////////////////

        // aspectX aspectY 是裁剪框宽高的比例
        intent.putExtra( "aspectX", 1);
        intent.putExtra( "aspectY", 1);
        // outputX outputY 是裁剪后生成图片的宽高
        intent.putExtra( "outputX", imgSize);
        intent.putExtra( "outputY", imgSize);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra( "return-data", true);
        fragment.startActivityForResult(intent, requestCode);
    }


    /**
     * 搜索本机上的所有图片
     * @return
     */
    public static List<String> searchAllPicInDevice(Context context){
        String filed = MediaStore.Images.Media.DATA;//图片路径字段
        List<Uri> uriList = new ArrayList<Uri>();
        uriList.add(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        uriList.add(MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        List<String> imgPaths = new ArrayList<String>();
        for(Uri item : uriList) {
            Cursor cursor = context.getContentResolver().query(item,null /*new String[]{filed}*/,null,null,null);
            while(cursor.moveToNext()){
                String filename = cursor.getString(cursor.getColumnIndex(filed));
                if(!TextUtils.isEmpty(filename)) imgPaths.add(filename);
            }
            cursor.close();
        }
        return imgPaths;
    }

    /** 重新计算图片框架尺寸 **/
    public static void resizeImageView(ImageView imgView,float scale){
        imgView.measure(0,0);
        int width = imgView.getMeasuredWidth();
        ViewGroup.LayoutParams lp = imgView.getLayoutParams();
        lp.height = (int)(width/scale);
        imgView.setLayoutParams(lp);
    }


}