package com.example.cmpe277_pocketnews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Constants {

    public static String formatDateTime(String apiDateResult) {
        String updatedDateTime="";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = sdf.parse(apiDateResult);
            updatedDateTime=sdf.format(date);
            return updatedDateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            return updatedDateTime;
        }
    }
    public static String getCountry() {
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }

}
