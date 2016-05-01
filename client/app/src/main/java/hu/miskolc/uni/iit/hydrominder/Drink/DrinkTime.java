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

    /**
     *
     * @param formatTime A normal date format, format example: year-month-dayThours:minutes:seconds 2016-06-01T19:25:12
     */
    public DrinkTime(String formatTime) {
        try {
            String[] tokens = stringTokenizer(formatTime);
            this.dateStringFormat = formatTime;
            year = Integer.valueOf(tokens[0]);
            month = Integer.valueOf(tokens[1]);
            day = Integer.valueOf(tokens[2]);
            hour = Integer.valueOf(tokens[3]);
            minute = Integer.valueOf(tokens[4]);
            second = Integer.valueOf(tokens[5]);
        } catch (Exception e) {

        }
    }

    /**
     * Initialize the DrinkTime with actual parameters.
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     */
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

    /**
     *
     * @param delay drinkFrequeny == hour
     * @param format true == full format year...seconds, otherwise hour...seconds
     * @return
     */
    public String calculateNextDrinkTime(double delay, boolean format) {
        if(format) {
            return year + "-" + month + "-" + day + " " + hour + Math.floor(delay) + ":" + minute + ":" + second;
        } else {
            return hour + Math.floor(delay) + ":" + minute + ":" + second;
        }
    }


    /**
     * Set the date-time with a format string.
     * @param dateTimeFormat A normal date format, format example: year-month-dayThours:minutes:seconds 2016-06-01T19:25:12
     * @return true if there was no error, false otherwise.
     */
    public boolean setDateWithFormat(String dateTimeFormat) {
        try {
            String[] tokens = stringTokenizer(dateTimeFormat);
            this.dateStringFormat = dateTimeFormat;
            year = Integer.valueOf(tokens[0]);
            month = Integer.valueOf(tokens[1]);
            day = Integer.valueOf(tokens[2]);
            hour = Integer.valueOf(tokens[3]);
            minute = Integer.valueOf(tokens[4]);
            second = Integer.valueOf(tokens[5]);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public String getNormalDateTime() {
        return this.year + "-" + this.month + "-" + this.day + " " + this.hour + ":" + this.minute + ":" + this.second;
    }
    /**
     *
     * @param formatted
     * @param mode true == year ... second, false == hour ... second
     * @return
     */
    private static String normalFormatter(String formatted, boolean mode) {
        String[] tokens = stringTokenizer(formatted);
        int fyear = Integer.valueOf( tokens[0]);
        int fmonth = Integer.valueOf(tokens[1]);
        int fday = Integer.valueOf(tokens[2]);
        int fhour = Integer.valueOf(tokens[3]);
        int fminute = Integer.valueOf(tokens[4]);
        int fsecond = Integer.valueOf(tokens[5]);
        if (mode) {
            return fyear + "-" + fmonth + "-" + fday + " " + fhour + ":" + fminute + ":" + fsecond;
        } else {
            return fhour + ":" + fminute + ":" + fsecond;
        }
    }

    public static String ConvertToNormalFormat(String formattedString, boolean mode) {
        return normalFormatter(formattedString, false);
    }

    public String getFormattedDate() {
        return this.dateStringFormat;
    }

    private static String[] stringTokenizer(String formattedText) {
        return formattedText.split("[:T-]");
    }

    @Override
    public String toString() {
        return "DrinkTime{" +
                "dateStringFormat='" + dateStringFormat + '\'' +
                '}';
    }
}
