package hu.miskolc.uni.iit.hydrominder.Drink;

/**
 * This class is used to hold an actual datepoint of drinking.
 * Created by Patrik on 2016.04.18..
 */
public class DrinkTime {

    /**
     * This 3 fields represent the year-month-day part of the whole date.
     */
    private int year;
    private int month;
    private int day;

    /**
     * This 3 fields represent the hour-minute-second part of the whole date.
     */
    private int hour;
    private int minute;
    private int second;

    /**
     * This is the string form of the date.
     * Example: 2016-05-04T15:14:12
     */
    private String dateStringFormat;

    public DrinkTime(String formatTime) {
        this.dateStringFormat = formatTime;
        String[] tokens = formatTime.split("[:-T]");
        year = Integer.valueOf( tokens[0]);
        month = Integer.valueOf(tokens[1]);
        day = Integer.valueOf(tokens[2]);
        hour = Integer.valueOf(tokens[3]);
        minute = Integer.valueOf(tokens[4]);
        second = Integer.valueOf(tokens[5]);

    }

    public DrinkTime(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.dateStringFormat = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
        this.dateStringFormat = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
        this.dateStringFormat = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
        this.dateStringFormat = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        this.dateStringFormat = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        this.dateStringFormat = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
        this.dateStringFormat = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;
    }

    @Override
    public String toString() {
        return "DrinkTime{" +
                "dateStringFormat='" + dateStringFormat + '\'' +
                '}';
    }
}
