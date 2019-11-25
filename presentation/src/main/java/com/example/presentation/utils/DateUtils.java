package com.example.presentation.utils;

import android.os.Build;


import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class DateUtils {


    private DateUtils() { }

    @NotNull
    public static String getDateOffset(int currentPosition) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -currentPosition);
        Date dateWithOffset = calendar.getTime();
        return formattingDate(dateWithOffset);
    }

    @NotNull
    private static String formattingDate(@NotNull Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static int getLengthOfYear() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return YearMonth.now().lengthOfYear();
        } else {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            Calendar gregorianCalendar = new GregorianCalendar(year, month, day);
            return gregorianCalendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        }
    }
}
