package Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtility {

    /**
     * Get a date-hour stamp as an integer
     * @return yyyyMMddhh
     */
    public static int getDateTimeId(){

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhh");
        Date date = new Date();
        int dateString = Integer.parseInt(dateFormat.format(date));
        System.out.println("Date string retrieved: " + dateString);
        return dateString;
    }

}
