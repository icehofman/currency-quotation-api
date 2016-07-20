package com.icehofman.currency.quotation.api.utils;

import com.icehofman.currency.quotation.api.exceptions.IllegalDateException;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getDate(String date) throws IllegalDateException {
        Date result = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            formatter.setLenient(false);
            result = formatter.parse(date);
        } catch (Exception e) {
            throw new IllegalDateException("Error to convert date = {" + date + "}");
        }
        return result;
    }

    public static String getStringDate(Date date) {
        String result = null;
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        result = formatter.format(date);
        return result;
    }

    public static Date getDateWithDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 7) {
            calendar = minusDays(calendar, 1);
        } else if (dayOfWeek == 1) {
            calendar = minusDays(calendar, 2);
        }
        return calendar.getTime();
    }

    private static Calendar minusDays(Calendar calendar, int days) {
        calendar.add(Calendar.DAY_OF_MONTH, days * -1);
        return calendar;
    }

    public static Date minusDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar = minusDays(calendar, days);
        return calendar.getTime();
    }

    private static Calendar plusDays(Calendar calendar, int days) {
        calendar.add(Calendar.DAY_OF_MONTH, +days);
        return calendar;
    }

    public static Date plusDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar = plusDays(calendar, days);
        return calendar.getTime();
    }
}
