package ru.zagorodnikova.tm.util;


import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterUtil {

    public static Date dateFormatter(@NotNull final String date) throws Exception {
        @NotNull final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.parse(date);
    }

    public static String dateFormatter(@NotNull final Date date) {
        @NotNull final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
