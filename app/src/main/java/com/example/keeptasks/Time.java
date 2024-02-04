package com.example.keeptasks;

import android.app.TimePickerDialog;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import android.widget.TimePicker;

public class Time {
    long current_time;
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

    public long futureTime(int year,int month,int day,int hour,int min){
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day,hour,min);
        return cal.getTimeInMillis();
    }
}
