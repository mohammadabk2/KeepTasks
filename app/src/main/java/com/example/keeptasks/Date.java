package com.example.keeptasks;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Date {
    private int year_f,month_f,day_f;

    public Date() {
    }

    public void setFutureDate(int year , int month, int day){
        this.year_f = year;
        this.month_f = month;
        this.day_f = day;
    }

    public int getYear(){
        return  this.year_f;
    }

    public int getMonth(){
        return  this.month_f;
    }

    public int getDay(){
        return  this.day_f;
    }
    public String makeDateString(int day, int month, int year) {
        return day + "/" + month + "/" + year;
    }

    public String getTodayDate() { 
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
}
