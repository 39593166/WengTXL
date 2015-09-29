package tool.io;

import android.graphics.Bitmap;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bj on 2015/7/29.
 * 文件处理工具类
 */
public class FileUtil {

    /**
     * 写入数据到本地文件
     * @param filename 文件名称
     * @param content 要写入的内容
     * @throws Exception
     */
    public static void write2file(String filename,String content) throws Exception{
        File file = new File(filename);
        File parentFile = file.getParentFile();
        if((parentFile.mkdirs() || parentFile.isDirectory())){
            if(file.exists()) file.delete();//如果文件已经存在，则删除该文件
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        }else{
            throw new Exception("写入文件异常");
        }
    }

    /**
     * 写入数据到本地文件
     * @param filename 文件名称
     * @param content 要写入的内容
     * @throws Exception
     */
    public static void write2file(String filename,byte[] content) throws Exception{
        File file = new File(filename);
        File parentFile = file.getParentFile();
        if((parentFile.mkdirs() || parentFile.isDirectory())){
            if(file.exists()) file.delete();//如果文件已经存在，则删除该文件
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content);
            fos.close();
        }else{
            throw new Exception("写入文件异常");
        }
    }

    /**
     * 写入数据到本地文件
     * @param filename 文件名称
     * @param stream 要写入的数据流
     * @throws Exception
     */
    public static void write2file(String filename,InputStream stream) throws Exception{
        File file = new File(filename);
        File parentFile = file.getParentFile();
        if((parentFile.mkdirs() || parentFile.isDirectory())){
            if(file.exists()) file.delete();//如果文件已经存在，则删除该文件
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[2048];
            int length = 0;//每次取得的数据长度，为-1则表示已经读取完整
            while((length = stream.read(buffer))!=-1)
                fos.write(buffer,0,length);
            stream.close();
            fos.close();
        }else{
            throw new Exception("写入文件异常");
        }
    }

    /** 数据流读取为字符串 **/
    public static String stream2string(InputStream stream) throws Exception{
        if(stream == null) return "";
        String lineContent = null;
        StringBuilder strBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        while((lineContent = reader.readLine())!=null)
            strBuilder.append(lineContent);
        reader.close();
        return strBuilder.toString();
    }

    /**  Bitmap文件 --->  byteArray  **/
    public static byte[] bmp2bytes(Bitmap bmp){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,bos);
        return bos.toByteArray();
    }

}
