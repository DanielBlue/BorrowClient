package pers.maoqi.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by maoqi on 2017/5/31.
 */

public class DateUtils {
    private static final String dtLong = "yyyyMMddHHmmss";

    private static final String simple = "yyyy-MM-dd HH:mm:ss";

    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    private static final String sim = "MM-dd HH:mm:ss";

    private static final String dtShort = "yyyyMMdd";

    private static SimpleDateFormat simpleDateFormat;

    public static String getOrderNum() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtLong, Locale.getDefault());
        return df.format(date);
    }

    public static String getDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simple, Locale.getDefault());
        return df.format(date);
    }

    public static String getFormatterDate() {
        simpleDateFormat = new SimpleDateFormat(simple, Locale.getDefault());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return simpleDateFormat.format(c.getTime());
    }

    public static String getDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(sim);
        return df.format(date);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param time
     * @return
     */

    public static long getStringToDate(String time) {

        simpleDateFormat = new SimpleDateFormat(simple, Locale.getDefault());
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 时间戳转换成字符窜
     *
     * @param time
     * @return
     */
    public static String getDateToString(long time, String dataFormat) {

        Date d = new Date(time);
        if (dataFormat == null) {
            simpleDateFormat = new SimpleDateFormat(simple, Locale.getDefault());
        } else {
            simpleDateFormat = new SimpleDateFormat(dataFormat,
                    Locale.getDefault());
        }
        return simpleDateFormat.format(d);
    }

    // 获取当前时间+ 55000
    public static String getStrTiem() {
        long time = getStringToDate(getDateFormatter());
        return getDateToString(time, null);
    }

    // Long型时间戳转换为字符串"yyyy-MM-dd"
    public static String getStrTime(Long time) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String re_strTime = simpleDateFormat.format(new Date(time * 1000L));
        return re_strTime;
    }

    public static String getStrTimeNew(Long time) {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String re_strTime = simpleDateFormat.format(new Date(time * 1000L));
        return re_strTime;
    }
}
