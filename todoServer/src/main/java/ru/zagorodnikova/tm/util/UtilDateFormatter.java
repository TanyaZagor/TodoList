package ru.zagorodnikova.tm.util;


import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDateFormatter {

    public static Date dateFormatter(@NotNull final String date) throws Exception {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.parse(date);
    }
}
