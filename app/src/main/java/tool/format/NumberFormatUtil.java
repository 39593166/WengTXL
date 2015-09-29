package tool.format;

import java.text.DecimalFormat;

/**
 * Created by bj on 2015/7/29.
 * 数字数据处理辅助类
 */
public class NumberFormatUtil {

    /** 格式化数字对象 **/
    public static String format(Object number,String format){
        return new DecimalFormat(format).format(number);
    }

    /** 格式化金钱数据,小数末尾有零的情况会自动去掉0 **/
    public static String formatMoney(float number){
        String money = format(number,"0.00");
        if(money.matches("\\d+[.][1-9][0]")){//末尾一个0的情况
            return money.substring(0,money.length()-1);
        }else if(money.matches("\\d+[.][0]{2}")){//末尾两个0的情况
            return money.split("[.]")[0];
        }else{
            return money;
        }
    }

}
