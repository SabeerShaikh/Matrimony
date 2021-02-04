package com.matrimony.module.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtls {

    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");

    public static String formatDayFormat(long dateInMillis) {
        return dayFormat.format(new Date(dateInMillis * 1000));
    }

}

