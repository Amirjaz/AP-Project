package business_domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Time Class is used to simplify saving time for orders.
 */
public class Time implements Serializable {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    Time(int year, int month, int day, int hour, int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
    Time(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = 0;
        this.minute = 0;
    }

    /**
     * Returns time in <Code>String</Code> format.
     * @return time in "YYYY-MM-DD" format
     */
    public String getDateString(){
        return ("" + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day));
    }

    /**
     * Returns time in <Code>String</Code> format.
     * @return time in "YYYY-MM-DD HH:MM" format
     */
    public String getDateTimeString(){
        return ("" + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day) + " " + String.format("%02d", hour) + ":" + String.format("%02d", minute));
    }

    /**
     * Get the current local time.
     * @return the current local time
     */
    public static Time now(){
        var dt = LocalDateTime.now();
        return new Time(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute());
    }
}
