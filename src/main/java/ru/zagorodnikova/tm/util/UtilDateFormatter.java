package ru.zagorodnikova.tm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDateFormatter {

    public static Date dateFormatter(String date) throws IllegalArgumentException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date newDate = null;
        try {
            newDate = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date format error");
        }
        return newDate;
    }
}
