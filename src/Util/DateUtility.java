package Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

    public static int getDateId(){

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        int dateString = Integer.parseInt(dateFormat.format(date));
        System.out.println("Date string retrieved: " + dateString);
        return dateString;
    }
}
