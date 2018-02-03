package Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

    public static String getDateString(){

        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        System.out.println("Date string retrieved: " + dateString);
        return dateString;
    }
}
