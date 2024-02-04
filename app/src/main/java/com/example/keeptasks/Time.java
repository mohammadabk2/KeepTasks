package com.example.keeptasks;

import android.app.TimePickerDialog;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import android.widget.TimePicker;

public class Time {
    long current_time;
    private  int hour,min;
    public Time() {

    }

    public String makeTimeString(int hour, int minutes) {
        String time = "";
        if (hour < 10) {
            time += "0";
        }
        time += hour + ":";
        if (minutes < 10) {
            time += "0";
        }
        time += minutes;
        return time;
    }

    public String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);
        this.current_time=cal.getTimeInMillis();
        return makeTimeString(hour, minutes);
    }

    public long timeInMillis(){

        return  this.current_time;
    }

    public long futureTime(Date dateObj){
        Calendar cal = Calendar.getInstance();
        cal.set(dateObj.getYear(),dateObj.getMonth(),dateObj.getDay(),this.hour,this.min);
        return cal.getTimeInMillis();
    }

    public int getHour(){
        return  this.hour;
    }

    public int getMin(){
        return this.min;
    }

    public void setFutureTime(int hour,int min){
        this.hour = hour;
        this.min = min;
    }
}
