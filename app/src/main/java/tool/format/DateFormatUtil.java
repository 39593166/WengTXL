package tool.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bj on 2015/7/29.
 * 时间格式化处理类
 */
public class DateFormatUtil {

    /**
     * 格式化Date数据
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date,String format){
        return new SimpleDateFormat(format).format(date);
    }

    /** 获取今天的时间数据 **/
    public static String getToday(String format){
        return format(new Date(), format);
    }

    /** 把日期数据转换为Long型数据 **/
    public static long date2long(Date date){
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /** 获取两个时间的毫秒差值 **/
    public static long diff(Date date1,Date date2){
        return date2long(date1) - date2long(date2);
    }

    /** 字符串转日期数据，抛出异常ParseException **/
    public static Date string2date(String dateString,String format)throws ParseException{
        return new SimpleDateFormat(format).parse(dateString);
    }

}
