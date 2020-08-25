package com.zuoqiang.test.tools.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author zuoqiang
 * @version 1.0
 * @description todo
 * @date 2020/8/25 10:11 上午
 */

public class DateUtil {
    public static Date getDate(String datestr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        try {
            dateFormat.setLenient(false);
            return dateFormat.parse(datestr);
        } catch (ParseException var4) {
            throw new RuntimeException("时间错误格式");
        }
    }

    public static Date getCurrDateTime() {
        return Calendar.getInstance().getTime();
    }

    public static Date getCurrDate() {
        Calendar now = Calendar.getInstance();
        return (new GregorianCalendar(now.get(1), now.get(2), now.get(5))).getTime();
    }

    public static String getCurrDateTimeStr() {
        return getCurrDateTimeStr("yyyyMMddHHmmssSSS");
    }

    public static String getCurrTimeStr() {
        return getCurrDateTimeStr("HHmmssSSS");
    }

    public static String getCurrDateTimeStr(String format) {
        Calendar now = Calendar.getInstance();
        return getDateTimeStr(now.getTime(), format);
    }

    public static String getDateTimeStr(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    public static Date parseDateTime(String str) {
        return parseDate(str, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseDate(String str) {
        return parseDate(str, "yyyy-MM-dd");
    }

    public static Date parseDate(String dateStr, String formatStr) {
        if (dateStr != null && dateStr.length() != 0) {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            Date date = null;

            try {
                date = format.parse(dateStr);
                return date;
            } catch (ParseException var5) {
                throw new RuntimeException("日期转换异常");
            }
        } else {
            return null;
        }
    }

    public static Date createByDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, days);
        return cal.getTime();
    }

    public static Date getHisdayBegin(Date date, int days) {
        Date d = createByDays(date, days);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getHisdayEnd(Date date, int days) {
        Date d = createByDays(date, days);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }

    public static Date getBeforeTime(int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, seconds);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(getBeforeTime(-1200));
    }
}
